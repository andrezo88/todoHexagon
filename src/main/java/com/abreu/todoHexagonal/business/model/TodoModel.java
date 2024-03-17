package com.abreu.todoHexagonal.business.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record TodoModel(
        String id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        boolean isLate,
        boolean completed,
        StatusEnum status
) {

}
