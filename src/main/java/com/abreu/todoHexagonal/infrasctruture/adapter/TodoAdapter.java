package com.abreu.todoHexagonal.infrasctruture.adapter;

import com.abreu.todoHexagonal.business.exception.IdNotFoundException;
import com.abreu.todoHexagonal.business.mapper.TodoMapperBusiness;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.business.service.ValidationService;
import com.abreu.todoHexagonal.business.service.port.TodoPort;
import com.abreu.todoHexagonal.infrasctruture.repository.TodoRepository;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class TodoAdapter implements TodoPort {

    private final TodoRepository repository;
    private final TodoMapperBusiness mapper;
    private final ValidationService validationService;

    public TodoAdapter(TodoRepository repository, TodoMapperBusiness mapper, ValidationService validationService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validationService = validationService;
    }

    @Override
    public TodoModel createTodo(TodoModel todo) {
        validationService.validatePastDate(todo);
        validationService.validatePriority(todo);
        TodoEntity entity = mapper.toEntity(todo);
        TodoEntity saved = repository.save(entity);
        return mapper.toModel(saved);

    }

    @Override
    public List<TodoModel> getAllTodos() {

        List<TodoEntity> entities = repository.findAll();

        return entities.stream().map(mapper::toModel).toList();
    }

    @Override
    public TodoModel getTodoById(String id) {
        return mapper.toModel(repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(
                        String.format("Todo with id %s not found", id)))
        );
    }

}