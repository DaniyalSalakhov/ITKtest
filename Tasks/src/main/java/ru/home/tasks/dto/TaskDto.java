package ru.home.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.home.tasks.models.Account;
import ru.home.tasks.models.Task;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Long accountId;

    public static TaskDto from(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        if(task.getAccount() != null) {
            dto.setAccountId(task.getAccount().getId());
        }
        return dto;
    }

    public static List<TaskDto> from(List<Task> tasks) {
        return tasks.stream().map(TaskDto::from).collect(Collectors.toList());
    }
}
