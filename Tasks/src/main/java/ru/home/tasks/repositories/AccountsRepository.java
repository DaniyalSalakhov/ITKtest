package ru.home.tasks.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.tasks.models.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Page<Account> findAll(Pageable pageable);
}
