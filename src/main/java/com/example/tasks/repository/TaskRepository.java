package com.example.tasks.repository;

import com.example.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task>findTaskByTaskName(String taskName);
    Optional<Task> findById(Long id);

}
