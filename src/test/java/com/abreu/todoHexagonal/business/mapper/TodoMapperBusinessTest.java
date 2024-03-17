package com.abreu.todoHexagonal.business.mapper;

import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.Priority;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static com.abreu.todoHexagonal.business.model.Priority.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TodoMapperBusinessTest {

    public static final TodoMapperBusiness INSTANCE_MAPPER = Mappers.getMapper(TodoMapperBusiness.class);

    @Test
    void shouldMapToModel() {


        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .completed(Boolean.TRUE)
                .build();

        TodoModel result = INSTANCE_MAPPER.toModel(entity);

        assertEquals(result, model);
    }

    @Test
    void shouldMapToModelPriorityMedium() {


        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(MEDIUM)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.MEDIUM)
                .completed(Boolean.TRUE)
                .build();

        TodoModel result = INSTANCE_MAPPER.toModel(entity);

        assertEquals(result, model);
    }

    @Test
    void shouldMapToModelPriorityLow() {


        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(LOW)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.LOW)
                .completed(Boolean.TRUE)
                .build();

        TodoModel result = INSTANCE_MAPPER.toModel(entity);

        assertEquals(result, model);
    }

    @Test
    void shouldMapToModelPriorityNull() {


        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(null)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(null)
                .completed(Boolean.TRUE)
                .build();

        TodoModel result = INSTANCE_MAPPER.toModel(entity);

        assertEquals(result, model);
    }

    @Test
    void shouldMapToEntity() {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .completed(Boolean.TRUE)
                .build();

        TodoEntity result = INSTANCE_MAPPER.toEntity(model);

        assertEquals(result, entity);
    }

    @Test
    void shouldMapToEntityPriorityMedium() {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(MEDIUM)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.MEDIUM)
                .completed(Boolean.TRUE)
                .build();

        TodoEntity result = INSTANCE_MAPPER.toEntity(model);

        assertEquals(result, entity);
    }

    @Test
    void shouldMapToEntityPriorityLow() {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(LOW)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.LOW)
                .completed(Boolean.TRUE)
                .build();

        TodoEntity result = INSTANCE_MAPPER.toEntity(model);

        assertEquals(result, entity);
    }

    @Test
    void shouldMapToEntityPriorityNull() {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(null)
                .completed(Boolean.TRUE)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(null)
                .completed(Boolean.TRUE)
                .build();

        TodoEntity result = INSTANCE_MAPPER.toEntity(model);

        assertEquals(result, entity);
    }

    @Test
    void shouldReturnNullWhenTodoModelIsNull() {

        TodoEntity result = INSTANCE_MAPPER.toEntity(null);

        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenTodoEntityIsNull() {

        TodoModel result = INSTANCE_MAPPER.toModel(null);

        assertNull(result);
    }


}
