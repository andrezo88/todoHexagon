package com.abreu.todoHexagonal.infrasctruture.adapter;

import com.abreu.todoHexagonal.business.exception.IdNotFoundException;
import com.abreu.todoHexagonal.business.mapper.TodoMapperBusiness;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.business.service.port.TodoPort;
import com.abreu.todoHexagonal.infrasctruture.repository.TodoRepository;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.StatusEnum;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component

public class TodoAdapter implements TodoPort {

    private final TodoRepository repository;
    private final TodoMapperBusiness mapper;

    private static final String ID_NOT_FOUND = "Todo with id %s not found";

    public TodoAdapter(TodoRepository repository, TodoMapperBusiness mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TodoModel createTodo(TodoModel todo) {
        TodoEntity entity = mapper.toEntity(todo);
        TodoEntity saved = repository.save(entity);
        return mapper.toModel(saved);

    }

    @Override
    public List<TodoModel> getAllTodos() {
        List<TodoEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toModel).toList();
    }

    private static void peformUpdateAt(TodoEntity entity) {
        if (entity.getUpdatedAt() == null) {
            entity.setUpdatedAt(new ArrayList<>());
        }
        entity.getUpdatedAt().add(LocalDateTime.now());
    }

    private static void peformCompleteTodo(TodoEntity entity) {
        entity.setCompleted(true);
        entity.setStatus(StatusEnum.DONE);
    }

    @Override
    public TodoModel getTodoById(String id) {
        return mapper.toModel(repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(
                        String.format(ID_NOT_FOUND, id)))
        );
    }

    @Override
    public TodoModel updateTodoById(String id, TodoModel model) {
        TodoEntity entity = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(
                        String.format(ID_NOT_FOUND, id)));
        peformUpdate(model, entity);
        TodoEntity saved = repository.save(entity);
        return mapper.toModelUpdate(saved);
    }

    private void peformUpdate(TodoModel model, TodoEntity entity) {
        mapper.toEntityUpdate(model, entity);
        peformUpdateAt(entity);
    }

    @Override
    public TodoModel completeTodoById(String id) {
        TodoEntity entity = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(
                        String.format(ID_NOT_FOUND, id)));
        peformCompleteTodo(entity);
        peformUpdateAt(entity);
        repository.save(entity);
        return mapper.toModelUpdate(entity);
    }
}
