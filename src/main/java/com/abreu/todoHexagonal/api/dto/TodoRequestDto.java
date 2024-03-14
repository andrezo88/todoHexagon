package com.abreu.todoHexagonal.api.dto;

import com.abreu.todoHexagonal.configuration.adapter.LocalDateTypeAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TodoRequestDto(
        @NotBlank(message = "Title cannot be null or empty")
        String title,
        @NotBlank(message = "Description cannot be null or empty")
        @Size(max = 100)
        String description,
        @NotNull(message = "Due date cannot be null")
        @JsonAdapter(LocalDateTypeAdapter.class)
        LocalDate dueDate,
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        Priority priority,
        Boolean status
) {
}
