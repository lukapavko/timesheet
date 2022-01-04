package com.example.tasks.service;

import com.example.tasks.model.Task;
import com.example.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class TaskService {
    private final TaskRepository taskUserRepository;

    @Autowired
    public TaskService(TaskRepository taskUserRepository) {
        this.taskUserRepository = taskUserRepository;
    }

    public List<Task> getAllTaskUsers() {
        return taskUserRepository.findAll();
    }

    public Optional<Task> findTaskUserById(Long taskUserId) {
        boolean taskUserExists = taskUserRepository.existsById(taskUserId);
        if (!taskUserExists) {
            throw new IllegalStateException(("Task with id: " + taskUserId + "NEMAAAAAAA!"));
        }
        return taskUserRepository.findById(taskUserId);

    }

    public void addNewTaskUser(Task request) {
        try {
            Optional<Task> taskUserByTaskName = taskUserRepository.findTaskByTaskName(request.getTaskName());
            if (taskUserByTaskName.isPresent()) {
                throw new IllegalStateException("Task name exists");
            } else {
                try {
                    taskUserRepository.save(request);
                } catch (Error error) {
                    System.out.println("Nece zato sto: " + error);

                }
            }
        } catch (Error error) {
            System.out.println("Nece zato sto: " + error);
        }
    }

    public void deleteTaskUserById(Long taskUserId) {
        boolean taskUserExists = taskUserRepository.existsById(taskUserId);
        if (!taskUserExists) {
            throw new IllegalStateException(("Task with id: " + taskUserId + "NEMAAAAAAA!"));
        } else {
            try {
                taskUserRepository.deleteById(taskUserId);
            } catch (Error error) {
                System.out.println("Neće bogati zato jer: " + error);
            }
        }
    }

    @Transactional
    public void updateTaskUser(Long taskUserId, String taskName) {
        Task task = taskUserRepository.findById(taskUserId).orElseThrow(
                () -> new IllegalStateException(
                        "Korisnik sa id:" + taskUserId + "NEMAAAAAAAAA!"
                )
        );
        if (taskName != null
                && taskName.length() > 0
                && !Objects.equals(task.getTaskName(), taskName)) {
            try {
                task.setTaskName(taskName);
            } catch (Error error) {
                System.out.println("Ne radi jer: " + error);
            }
        }

    }
//testing
   /* public String startTask()  {
        Vrijeme kupus = new.Vrijeme();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Formatted LocalDateTime : " + formattedDateTime);
        kupus.setStartTajm(formattedDateTime);
        taskUserRepository.save(kupus);
        return formattedDateTime;
    }
    */

}
