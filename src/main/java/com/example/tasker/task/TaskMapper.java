package com.example.tasker.task;

import com.example.tasker.task.dto.TaskDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    @Mapping(source = "accountId", target = "account.id")
    Task toEntity(TaskDto taskDto);

    @Mapping(source = "account.id", target = "accountId")
    TaskDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "accountId", target = "account.id")
    Task partialUpdate(TaskDto taskDto, @MappingTarget Task task);
}