package ru.home.tasks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.home.tasks.dto.TaskDto;
import ru.home.tasks.dto.TaskUpdEventDto;
import ru.home.tasks.dto.TasksPage;
import ru.home.tasks.exceptions.TaskNotFoundException;
import ru.home.tasks.models.Account;
import ru.home.tasks.models.Task;
import ru.home.tasks.repositories.AccountsRepository;
import ru.home.tasks.repositories.TasksRepository;
import ru.home.tasks.services.TaskUpdEventProducer;
import ru.home.tasks.services.TasksService;


import static ru.home.tasks.dto.TaskDto.from;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;
    private final AccountsRepository accountsRepository;
    private final TaskUpdEventProducer taskUpdEventProducer;

    @Value("${default-page-size}")
    private int defaultPageSize;

    @Override
    public TasksPage findAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Task> tasksPage = tasksRepository.findAll(pageRequest);
        return TasksPage.builder()
                .tasks(from(tasksPage.getContent()))
                .totalPages(tasksPage.getTotalPages())
                .build();
    }

    @Override
    public TaskDto findById(Long taskId) {
        return from(tasksRepository.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @Override
    public TaskDto createTask(TaskDto newTask) {
        TaskDto task = from(tasksRepository.save(
                Task.builder()
                        .name(newTask.getName())
                        .description(newTask.getDescription())
                        .status(newTask.getStatus())
                        .build()));
        taskUpdEventProducer.sendTaskUpdEvent(TaskUpdEventDto.builder()
                        .id(task.getId())
                        .title("New task created")
                        .build());
        return task;
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto newData){
        Task task = tasksRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if(newData.getStatus() != null) {
            task.setStatus(newData.getStatus());
        } else if (newData.getAccountId() != null) {
            Account account = accountsRepository.findById(newData.getAccountId()).orElse(null);
            task.setAccount(account);
            taskUpdEventProducer.sendTaskUpdEvent(TaskUpdEventDto.builder()
                            .id(taskId)
                            .title("New user upd")
                            .build());
        }
        return from(tasksRepository.save(task));
    }
}
