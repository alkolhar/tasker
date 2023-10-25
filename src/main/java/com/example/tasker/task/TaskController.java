package com.example.tasker.task;

import com.example.tasker.task.dto.TaskDto;
import com.example.tasker.task.dto.TaskDtoModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskDtoModelAssembler modelAssembler;

    public TaskController(TaskService taskService, TaskDtoModelAssembler modelAssembler) {
        this.taskService = taskService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<TaskDto>> findAllTasks(@RequestParam(required = false) Long accountId) {
        List<Task> tasksFound;
        if (accountId == null) {
            tasksFound = taskService.findAll();
        } else {
            tasksFound = taskService.findByCreatorId(accountId);
        }
        return ResponseEntity.ok(modelAssembler.toCollectionModel(tasksFound));
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable Long id) {
        Task byTaskId = taskService.findByTaskId(id);
        TaskDto model = modelAssembler.toModel(byTaskId);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createNewTask(@RequestBody TaskDto task) {
        Task save = taskService.createNewTask(task);
        TaskDto model = modelAssembler.toModel(save);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .build();
    }
}
