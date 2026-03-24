package ru.home.tasks.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.home.tasks.dto.TaskDto;
import ru.home.tasks.dto.TasksPage;
import ru.home.tasks.services.TasksService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;

    @GetMapping
    public ResponseEntity<TasksPage> getTasks(@RequestParam("page") int page) {
        return ResponseEntity.ok(tasksService.findAll(page));
    }

    @GetMapping("/{task-id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("task-id")  Long taskId) {
        return ResponseEntity.ok(tasksService.findById(taskId));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tasksService.createTask(task));
    }

    @PutMapping("/{task-id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("task-id") Long taskId, @RequestBody TaskDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(tasksService.updateTask(taskId, newData));
    }
}
