package com.abreu.todoHexagonal.api.mapper;

import com.abreu.todoHexagonal.api.dto.Priority;
import com.abreu.todoHexagonal.api.dto.TodoRequestDto;
import com.abreu.todoHexagonal.api.dto.TodoResponseDto;
import com.abreu.todoHexagonal.business.model.TodoModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static com.abreu.todoHexagonal.business.model.Priority.HIGH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoMapperTest {

    public static final TodoMapper INSTANCE_MAPPER = Mappers.getMapper(TodoMapper.class);


    @Test
    void shouldMapToModel() {

        TodoRequestDto dto = TodoRequestDto.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(Priority.HIGH)
                .build();
        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.FALSE)
                .build();

        TodoModel result = INSTANCE_MAPPER.toModel(dto);

        assertEquals(result, model);
    }

    @Test
    void shouldMapToResponse() {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.TRUE)
                .build();

        TodoResponseDto result = INSTANCE_MAPPER.toResponse(model);

        assertEquals(result.description(), model.description());
        assertEquals(result.dueDate(), model.dueDate());
    }
}
