package ru.home.tasks.services;

import ru.home.tasks.dto.TaskUpdEventDto;

public interface TaskUpdEventProducer {
    void sendTaskUpdEvent(TaskUpdEventDto event);
}
