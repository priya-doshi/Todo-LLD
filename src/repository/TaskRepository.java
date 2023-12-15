package repository;

import entities.Task;
import exceptions.ExistingTaskException;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {

    public static Map<String, Task> taskList = new HashMap<>();
    public Task addTask(Task task) throws ExistingTaskException {
        if(taskList.containsKey(task.getName())){
            throw new ExistingTaskException("Task exists with name : " + task.getName());
        }
        taskList.put(task.getName(), task);
        return task;
    }

    public Task getTask(String name){
        if(!taskList.containsKey(name)){
            throw new IllegalArgumentException("Task with the name " + name +" doesn't exists");
        }
        return taskList.get(name);
    }

    public Task modifyTask(Task task) {
        if(!taskList.containsKey(task.getName())){
            throw new IllegalArgumentException("Task with the name " + task.getName() +" doesn't exists");
        }
        taskList.put(task.getName(), task);
        return task;
    }

    public void removeTask(String name){
        taskList.remove(name);
    }

}
