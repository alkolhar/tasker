package com.example.tasker.task;

import com.example.tasker.account.Account;
import com.example.tasker.account.AccountRepository;
import com.example.tasker.task.dto.TaskDto;
import com.example.tasker.task.dto.TaskMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository,
                       AccountRepository accountRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByCreatorId(Long accountId) {
        return taskRepository.findByAccountId(accountId);
    }

    public Task createNewTask(TaskDto createTaskDto) {
        Task task = taskMapper.toEntity(createTaskDto);
        Account account = accountRepository.findById(createTaskDto.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account with id " + createTaskDto.getAccountId() + " not found"));
        task.setAccount(account);

        return taskRepository.save(task);
    }

    public Task findByTaskId(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
