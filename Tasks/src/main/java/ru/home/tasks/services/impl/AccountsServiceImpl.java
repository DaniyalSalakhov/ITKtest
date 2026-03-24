package ru.home.tasks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.tasks.dto.AccountDto;
import ru.home.tasks.dto.AccountsPage;
import ru.home.tasks.exceptions.AccountNotFoundException;
import ru.home.tasks.models.Account;
import ru.home.tasks.repositories.AccountsRepository;
import ru.home.tasks.services.AccountsService;
import static ru.home.tasks.dto.AccountDto.from;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    @Override
    public AccountDto findById(Long accountId) {
        return from(accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new));
    }
}
