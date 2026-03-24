package ru.home.tasks.services;

import ru.home.tasks.dto.TaskDto;
import ru.home.tasks.dto.TasksPage;

public interface TasksService {
    TasksPage findAll(int page);

    TaskDto findById(Long taskId);

    TaskDto createTask(TaskDto task);

    TaskDto updateTask(Long taskId, TaskDto newData);
}
