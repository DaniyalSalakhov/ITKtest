package ru.home.tasks.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.home.tasks.dto.AccountDto;
import ru.home.tasks.services.AccountsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountsService accountService;

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("account-id") Long accountId) {
        return ResponseEntity.ok(accountService.findById(accountId));
    }
}
