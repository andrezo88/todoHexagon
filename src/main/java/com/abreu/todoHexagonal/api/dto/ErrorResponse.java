package com.abreu.todoHexagonal.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(
        String message,
        Integer status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<String> errors,
        String path
) {
}