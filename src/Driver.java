import entities.ActivityLog;
import entities.Task;
import enums.FilterType;
import enums.TaskStatus;
import enums.TaskTag;
import exceptions.ExistingTaskException;
import service.TaskService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Driver {

    public static void main(String args[]) throws ExistingTaskException {

        TaskService taskService = new TaskService();
        Date date = new Date();

        long time = date.getTime() + 8 * 24 * 60 * 60 * 1000;

        System.out.println("Creating task");

        taskService.addTask("task-1", "doing task for product", Arrays.asList(TaskTag.PRODUCT), new Date(time));
        taskService.addTask("task-2", "doing task for engineer", Arrays.asList(TaskTag.ENGINEERING), new Date(time));
        taskService.addTask("task-3", "doing task for design and product", Arrays.asList(TaskTag.PRODUCT, TaskTag.DESIGN), new Date(time));

        Task task = taskService.getTask("task-1");

        System.out.println("Getting task-1");

        System.out.println("Task name : " + task.getName() + "\n" +
                "Task description : " + task.getDescription() + "\n" +
                "Task deadline : " + task.getDeadline());

        long newTime = date.getTime() + 15 * 24 * 60 * 60 * 1000;

        taskService.modifyTask("task-1", "doing task for product", new Date(newTime));

        System.out.println("Modifying task-1");

        System.out.println("Task name : " + task.getName() + "\n" +
                "Task description : " + task.getDescription() + "\n" +
                "Task deadline : " + task.getDeadline());

        taskService.removeTask("task-2");

        System.out.println("Modifying task-1 by status");

        taskService.changeStatus("task-1", TaskStatus.IN_PROGRESS);

        List<Task> tasks = taskService.filterTasks(FilterType.IN_PROGRESS_TASK_STATUS);

        System.out.println("Getting task by Status");

        tasks.forEach(task1 -> System.out.println("Task name : " + task1.getName() + "\n" +
                "Task description : " + task1.getDescription() + "\n" +
                "Task deadline : " + task1.getDeadline()));

        tasks = taskService.filterTasks(FilterType.ASCENDING_DEADLINE);


        System.out.println("Getting task by deadline");

        tasks.forEach(task1 -> System.out.println("Task name : " + task1.getName() + "\n" +
                "Task description : " + task1.getDescription() + "\n" +
                "Task deadline : " + task1.getDeadline()));

        System.out.println("Activity logs for tasks");


        tasks.forEach(task2 -> {
            List<ActivityLog> activityLogs = task2.getActivityLogs();
            if (activityLogs != null) {
                activityLogs.forEach(activityLog -> {
                    String action = activityLog.getAction().toString();
                    if (action != null) {
                        System.out.println(task2.getName() + " " + action);
                    }
                });
            }

        });
    }

}
