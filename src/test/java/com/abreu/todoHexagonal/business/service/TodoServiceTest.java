package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.Priority;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.business.service.port.TodoPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoPort todoPort;

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private TodoService todoService;

    @Test
    void testCreatedTodo() {
        TodoModel todo = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .build();

        when(todoPort.createTodo(todo)).thenReturn(todo);
        var result = todoService.createdTodo(todo);

        assertThat(result).isEqualTo(todo);
        verify(todoPort).createTodo(todo);
    }

    @Test
    void shouldThrowBadRequestException() {
        TodoModel todo = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2024-01-01"))
                .priority(Priority.HIGH)
                .build();

        when(validationService.validatePastDate(todo)).thenThrow(new BadRequestException("Date cannot be in the past"));
        assertThatThrownBy(() -> todoService.createdTodo(todo))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Date cannot be in the past");

        verifyNoInteractions(todoPort);
        verify(validationService).validatePastDate(todo);
    }

    @Test
    void testGetAllTodo() {
        when(todoPort.getAllTodos()).thenReturn(null);
        todoService.getAllTodo();
        verify(todoPort).getAllTodos();
    }

    @Test
    void testGetTodoById() {
        when(todoPort.getTodoById("1")).thenReturn(null);
        todoService.getTodoById("1");
        verify(todoPort).getTodoById("1");
    }

    @Test
    void testUpdateTodoById() {
        when(todoPort.updateTodoById("1", null)).thenReturn(null);
        todoService.updateTodoById("1", null);
        verify(todoPort).updateTodoById("1", null);
    }

    @Test
    void testCompleteTodoById() {
        when(todoPort.completeTodoById("1")).thenReturn(null);
        todoService.completeTodoById("1");
        verify(todoPort).completeTodoById("1");
    }
}
