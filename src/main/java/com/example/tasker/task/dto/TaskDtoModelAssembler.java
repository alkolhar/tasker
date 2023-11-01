package com.example.tasker.task.dto;

import com.example.tasker.task.Task;
import com.example.tasker.task.TaskController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskDtoModelAssembler implements RepresentationModelAssembler<Task, TaskDto> {

    private final TaskMapper taskMapper;

    public TaskDtoModelAssembler(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @NonNull
    @Override
    public TaskDto toModel(@NonNull Task task) {
        TaskDto taskDto = taskMapper.toDto(task);

        Link selfLink = linkTo(methodOn(TaskController.class).findTaskById(task.getId())).withSelfRel();
        taskDto.add(selfLink);

        return taskDto;
    }

    @NonNull
    @Override
    public CollectionModel<TaskDto> toCollectionModel(Iterable<? extends Task> taskList) {
        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task : taskList) {
            TaskDto taskDto = toModel(task);
            taskDtoList.add(taskDto);
        }

        return CollectionModel.of(taskDtoList);
    }
}
