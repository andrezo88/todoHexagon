package com.abreu.todoHexagonal.business.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TodoModel(
        String id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Boolean status
) {
}
