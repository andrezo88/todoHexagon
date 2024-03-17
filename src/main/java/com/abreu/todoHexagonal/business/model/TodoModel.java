package com.abreu.todoHexagonal.business.model;

import com.abreu.todoHexagonal.configuration.adapter.LocalDateTimeTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record TodoModel(
        String id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        boolean isLate,
        boolean completed,
        StatusEnum status,
        @JsonAdapter(LocalDateTimeTypeAdapter.class)
        LocalDateTime createdAt
) {
    public static class TodoModelBuilder {
        LocalDateTime createdAt = LocalDateTime.now();
    }


}
