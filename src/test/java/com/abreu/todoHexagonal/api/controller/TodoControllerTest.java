package com.abreu.todoHexagonal.api.controller;

import com.abreu.todoHexagonal.api.dto.Priority;
import com.abreu.todoHexagonal.api.dto.TodoRequestDto;
import com.abreu.todoHexagonal.api.dto.TodoResponseDto;
import com.abreu.todoHexagonal.api.dto.TodoUpdateDto;
import com.abreu.todoHexagonal.api.mapper.TodoMapper;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.business.service.TodoService;
import com.abreu.todoHexagonal.business.service.port.TodoPort;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.abreu.todoHexagonal.business.model.Priority.HIGH;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoController.class)
@AutoConfigureMockMvc(addFilters = false)
class TodoControllerTest {

    @MockBean
    TodoService service;

    @MockBean
    TodoMapper mapper;

    @MockBean
    TodoPort port;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateTodo() throws Exception {
        URI uri = URI.create("/api/v1/todo/1");
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
                .completed(Boolean.TRUE)
                .build();
        TodoResponseDto response = TodoResponseDto.builder().title("Test")
                .id("1")
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.now())
                .priority(Priority.HIGH)
                .completed(Boolean.TRUE)
                .build();
        var json = new Gson().toJson(dto);

        when(mapper.toModel(dto)).thenReturn(model);
        when(service.createdTodo(model)).thenReturn(model);
        when(mapper.toResponse(mapper.toModel(dto))).thenReturn(response);

        mockMvc.perform(post("/api/v1/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllTodos() throws Exception {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.TRUE)
                .build();
        TodoResponseDto response = TodoResponseDto.builder().title("Test")
                .id("1")
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.now())
                .priority(Priority.HIGH)
                .completed(Boolean.TRUE)
                .build();

        when(service.getAllTodo()).thenReturn(List.of(model));
        when(mapper.toResponse(model)).thenReturn(response);

        mockMvc.perform(get("/api/v1/todo"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetTodoById() throws Exception {

        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.TRUE)
                .build();
        TodoResponseDto response = TodoResponseDto.builder().title("Test")
                .id("1")
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.now())
                .priority(Priority.HIGH)
                .completed(Boolean.TRUE)
                .build();

        when(service.getTodoById("1")).thenReturn(model);
        when(mapper.toResponse(model)).thenReturn(response);

        mockMvc.perform(get("/api/v1/todo/1"))
                .andExpect(status().isOk());

        verify(port).getTodoById("1");
    }

    @Test
    void shouldUpdateTodo() throws Exception {
        TodoUpdateDto dto = TodoUpdateDto.builder()
                .priority(Priority.MEDIUM)
                .build();
        TodoModel model = TodoModel.builder()
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.parse("2025-01-01"))
                .priority(HIGH)
                .completed(Boolean.TRUE)
                .build();
        TodoResponseDto response = TodoResponseDto.builder().title("Test")
                .id("1")
                .title("Test")
                .description("Test")
                .dueDate(LocalDate.now())
                .priority(Priority.MEDIUM)
                .completed(Boolean.TRUE)
                .build();
        var json = new Gson().toJson(dto);

        when(mapper.toModelUpdate(dto)).thenReturn(model);
        when(service.updateTodoById("1", model)).thenReturn(model);
        when(mapper.toResponse(mapper.toModelUpdate(dto))).thenReturn(response);

        mockMvc.perform(patch("/api/v1/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

}
