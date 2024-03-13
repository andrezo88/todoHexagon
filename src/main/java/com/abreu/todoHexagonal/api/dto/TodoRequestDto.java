package com.abreu.todoHexagonal.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TodoRequestDto(
        @NotBlank(message = "Title cannot be null or empty")
        String title,
        @NotBlank(message = "Description cannot be null or empty")
        @Size(max = 100)
        String description,
        @NotNull(message = "Due date cannot be null")
        LocalDate dueDate,
        //@NotNull(message = "Priority cannot be null")
        Priority priority,
        Boolean status
) {
}
