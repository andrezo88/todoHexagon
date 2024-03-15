package com.abreu.todoHexagonal.api.dto;


import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record TodoResponseDto(
        String id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        boolean isLate,
        boolean completed
) {

}
