package com.abreu.todoHexagonal.infrasctruture.adapter;

import com.abreu.todoHexagonal.business.exception.IdNotFoundException;
import com.abreu.todoHexagonal.business.mapper.TodoMapperBusiness;
import com.abreu.todoHexagonal.business.model.Priority;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.TodoRepository;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.StatusEnum;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.abreu.todoHexagonal.business.model.StatusEnum.TO_DO;
import static com.abreu.todoHexagonal.infrasctruture.repository.entity.Priority.HIGH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoAdapterTest {

    @Spy
    private TodoMapperBusiness mapper;

    @Mock
    private TodoRepository repository;

    @InjectMocks
    private TodoAdapter adapter;

    @Test
    void shouldCreateTodo() {

        TodoModel todo = TodoModel.builder()
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(Priority.HIGH)
                .build();

        TodoEntity entity = TodoEntity.builder()
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(HIGH)
                .status(StatusEnum.TO_DO)
                .build();

        when(mapper.toEntity(todo)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(todo);

        var result = adapter.createTodo(todo);

        assertEquals(todo, result);
        verify(mapper).toEntity(todo);
        verify(repository).save(entity);
        verify(mapper).toModel(entity);
    }

    @Test
    void shouldGetAllTodos() {
        var entity = TodoEntity.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(HIGH)
                .status(StatusEnum.TO_DO)
                .build();

        when(repository.findAll()).thenReturn(java.util.List.of(entity));
        when(mapper.toModel(entity)).thenReturn(TodoModel.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(Priority.HIGH)
                .build());

        var result = adapter.getAllTodos();

        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toModel(entity);
    }

    @Test
    void shouldGetTodoById() {
        var entity = TodoEntity.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(HIGH)
                .status(StatusEnum.TO_DO)
                .build();

        when(repository.findById("1")).thenReturn(Optional.ofNullable(entity));
        when(mapper.toModel(entity)).thenReturn(TodoModel.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(Priority.HIGH)
                .status(TO_DO)
                .build());

        var expectedModel = mapper.toModel(entity);
        var actualModel = adapter.getTodoById("1");

        assertEquals(expectedModel, actualModel);
        verify(repository).findById("1");
        verify(mapper, times(2)).toModel(entity);
    }

    @Test
    void shouldReturnErrorWhenGetTodoByIdNotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adapter.getTodoById("1"))
                .isInstanceOf(IdNotFoundException.class)
                .hasMessageContaining("Todo with id 1 not found");

        verify(repository).findById("1");
    }

    @Test
    void shouldUpdateTodoById() {
        var entity = TodoEntity.builder()
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(HIGH)
                .status(StatusEnum.TO_DO)
                .build();

        var todo = TodoModel.builder()
                .title("Title")
                .description("Description")
                .dueDate(LocalDate.parse("2021-12-12"))
                .priority(Priority.HIGH)
                .build();

        when(repository.findById("1")).thenReturn(java.util.Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toModelUpdate(entity)).thenReturn(todo);

        var result = adapter.updateTodoById("1", todo);

        assertEquals(todo, result);
        verify(repository).findById("1");
        verify(repository).save(entity);
        verify(mapper).toModelUpdate(entity);
    }

    @Test
    void shouldReturnErrorWhenUpdateTodoByIdNotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adapter.updateTodoById("1", TodoModel.builder().build()))
                .isInstanceOf(IdNotFoundException.class)
                .hasMessageContaining("Todo with id 1 not found");

        verify(repository).findById("1");
    }


}
