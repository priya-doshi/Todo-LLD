package service;

import entities.Task;
import repository.TaskRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterByDeadlineStrategy implements FilterStrategy {

    @Override
    public List<Task> filter(){
        Map<String, Task> taskList = TaskRepository.taskList;
        return taskList.values().stream()
                .sorted(Comparator.comparing(Task::getDeadline))
                .collect(Collectors.toList());
    }
}
