package service;

import entities.ActivityLog;
import entities.Task;
import enums.Action;
import enums.FilterType;
import enums.TaskStatus;
import enums.TaskTag;
import exceptions.ExistingTaskException;
import repository.ActivityLogRepository;
import repository.TaskRepository;
import utils.Utility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskService {

    private  TaskRepository taskRepository;

    private FilterFactory filterFactory;

    private ActivityLogRepository activityLogRepository;

    public TaskService(){
        taskRepository = new TaskRepository();
        filterFactory = new FilterFactory();
        activityLogRepository = new ActivityLogRepository();
    }

    public String addTask(String name, String description, List<TaskTag> taskTagList, Date deadline) throws ExistingTaskException {
        Task task = new Task(name, description, taskTagList, deadline);
        task.setTaskId(Utility.generateNextTaskId());
        task.setTaskStatus(TaskStatus.OPEN);
        task.setActivityLogs(activityLogRepository.addActivityLog(Action.CREATED, new ArrayList<>()));
        taskRepository.addTask(task);
        return task.getTaskId();
    }

    public Task getTask(String name){
        Task task = taskRepository.getTask(name);
        return task;
    }

    public Task modifyTask(String name, String description, Date deadline) {
        Task task = taskRepository.getTask(name);
        task.setDeadline(deadline);
        task.setDescription(description);
        task.setActivityLogs(activityLogRepository.addActivityLog(Action.MODIEFIED, task.getActivityLogs()));
        taskRepository.modifyTask(task);
        return task;
    }

    public void removeTask(String name){
        Task task = taskRepository.getTask(name);
        task.setActivityLogs(activityLogRepository.addActivityLog(Action.REMOVED, task.getActivityLogs()));
      taskRepository.removeTask(name);
    }

    public Task changeStatus(String name, TaskStatus status) {
        Task task = taskRepository.getTask(name);
        task.setTaskStatus(status);
        task.setActivityLogs(activityLogRepository.addActivityLog(Action.MODIEFIED, task.getActivityLogs()));
        taskRepository.modifyTask(task);
        return task;
    }

    public List<Task> filterTasks(FilterType filterType){
       FilterStrategy filterStrategy = filterFactory.getFilterBy(filterType);
       return filterStrategy.filter();
    }




}
