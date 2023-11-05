package com.example.tasker.task.dto;

import com.example.tasker.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Task}
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto extends RepresentationModel<TaskDto> implements Serializable {
    @NotNull
    private Long id;
    private Long accountId;
    private String description;
    @NotBlank
    private String subject;


    public TaskDto(Long id, Long accountId, String description, String subject) {
        this.id = id;
        this.accountId = accountId;
        this.description = description;
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto entity = (TaskDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.subject, entity.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, description, subject);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "accountId = " + accountId + ", " +
                "description = " + description + ", " +
                "subject = " + subject + ")";
    }
}