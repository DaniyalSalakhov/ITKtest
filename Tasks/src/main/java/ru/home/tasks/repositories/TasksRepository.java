package ru.home.tasks.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.tasks.models.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {
    Page<Task> findAll(Pageable pageable);

}
