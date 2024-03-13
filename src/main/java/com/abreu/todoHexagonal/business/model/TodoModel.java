package com.abreu.todoHexagonal.business.model;

import java.time.LocalDate;

public record TodoModel(
        String id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Boolean status
) {
}
