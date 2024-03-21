package com.abreu.todoHexagonal.api.controller;

import com.abreu.todoHexagonal.api.dto.TodoRequestDto;
import com.abreu.todoHexagonal.api.dto.TodoResponseDto;
import com.abreu.todoHexagonal.api.dto.TodoUpdateDto;
import com.abreu.todoHexagonal.api.mapper.TodoMapper;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.business.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")

public class TodoController {

    private final TodoService service;

    private final TodoMapper mapper;

    public TodoController(TodoService service, TodoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto dto) {

        TodoModel model = mapper.toModel(dto);
        TodoModel todo = service.createdTodo(model);
        TodoResponseDto response = mapper.toResponse(todo);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        return ResponseEntity.ok().body(
                service.getAllTodo()
                        .stream()
                        .map(mapper::toResponse)
                        .toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable String id) {

        TodoModel todo = service.getTodoById(id);
        TodoResponseDto response = mapper.toResponse(todo);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable String id, @Valid @RequestBody TodoUpdateDto dto) {

        TodoModel model = mapper.toModelUpdate(dto);
        TodoModel todo = service.updateTodoById(id, model);
        TodoResponseDto response = mapper.toResponse(todo);

        return ResponseEntity.ok().body(response);
    }
}
