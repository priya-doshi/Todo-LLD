package service;

import entities.Task;

import java.util.List;

public interface FilterStrategy {
    List<Task> filter();
}
