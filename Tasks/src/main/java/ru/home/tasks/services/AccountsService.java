package ru.home.tasks.services;

import ru.home.tasks.dto.AccountDto;

public interface AccountsService {
    AccountDto findById(Long accountId);
}
