package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.Priority;
import com.abreu.todoHexagonal.business.model.StatusEnum;
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
    void shouldNotThrowBadRequestExceptionWhenValidatePastDateIsTrue() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .build();
        assertThat(validationService.validatePastDate(todoModel)).isTrue();
    }

    @Test
    void shoudlValidateIfTodoIsCompleted() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .completed(true)
                .build();
        assertThatThrownBy(() -> validationService.validateCompleted(todoModel))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Todo is already completed");
    }

    @Test
    void shouldNotThrowBadRequestExceptionWhenValidateCompletedIsTrue() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .build();
        assertThat(validationService.validateCompleted(todoModel)).isTrue();
    }

    @Test
    void shouldThrowBadRequestExceptionWhenStatusIsTheSame() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .status(StatusEnum.DONE)
                .build();
        TodoModel todoModelOld = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .status(StatusEnum.DONE)
                .build();
        assertThatThrownBy(() -> validationService.validateStatus(todoModel, todoModelOld))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Status cannot be the same");
    }

    @Test
    void shouldNotThrowBadRequestExceptionWhenValidateStatusIsTrue() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .status(StatusEnum.DONE)
                .build();
        TodoModel todoModelOld = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .status(StatusEnum.TO_DO)
                .build();
        assertThat(validationService.validateStatus(todoModel, todoModelOld)).isTrue();
    }

    @Test
    void shouldThrowBadRequestExceptionWhenStatusIsDone() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .status(StatusEnum.DONE)
                .build();
        assertThatThrownBy(() -> validationService.validateIFIsStatusDone(todoModel))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Status cannot be DONE");
    }

    @Test
    void shouldNotThrowBadRequestExceptionWhenValidateIFIsStatusDoneIsTrue() {
        TodoModel todoModel = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .status(StatusEnum.TO_DO)
                .build();
        assertThat(validationService.validateIFIsStatusDone(todoModel)).isTrue();
    }
}
