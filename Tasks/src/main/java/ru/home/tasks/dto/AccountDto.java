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
public class AccountDto {
    private long id;
    private String name;
    private String email;
    private List<Long> taskId;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .taskId(account.getTasks().stream().map(Task::getId).collect(Collectors.toList()))
                .build();
    }

    public static List<AccountDto> from(List<Account> accounts){
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
