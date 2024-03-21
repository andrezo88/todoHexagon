package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.Priority;
import com.abreu.todoHexagonal.business.model.TodoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Test
    void shouldThrowBadRequestException() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2024-01-01"))
                .priority(Priority.HIGH)
                .build();
        assertThatThrownBy(() -> validationService.validatePastDate(todoModel))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Date cannot be in the past");
    }

    @Test
    void shouldNotThrowBadRequestException() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .build();
        assertThat(validationService.validatePastDate(todoModel)).isTrue();
    }
}
