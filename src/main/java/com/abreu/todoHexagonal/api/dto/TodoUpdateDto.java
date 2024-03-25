package com.abreu.todoHexagonal.api.dto;

import com.abreu.todoHexagonal.configuration.adapter.LocalDateTypeAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TodoUpdateDto(
        String title,
        @Size(max = 100)
        String description,
        @JsonAdapter(LocalDateTypeAdapter.class)
        LocalDate dueDate,
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        Priority priority,
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        StatusEnum status
) {

}
