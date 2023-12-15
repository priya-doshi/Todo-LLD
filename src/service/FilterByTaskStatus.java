package service;

import entities.Task;
import enums.TaskStatus;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterByTaskStatus implements FilterStrategy{

    @Override
    public List<Task> filter(){
        Map<String, Task> taskList = TaskRepository.taskList;
        List<Task> tasks = new ArrayList<>();

        for (Task task : taskList.values()) {
            if (TaskStatus.IN_PROGRESS.equals(task.getTaskStatus() )) {
                tasks.add(task);
            }
        }

        return tasks;

    }
}
