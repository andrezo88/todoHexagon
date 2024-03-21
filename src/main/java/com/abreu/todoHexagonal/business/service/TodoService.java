package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.business.service.port.TodoPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoPort port;
    private final ValidationService validationService;

    public TodoService(TodoPort port, ValidationService validationService) {
        this.port = port;
        this.validationService = validationService;
    }


    public TodoModel createdTodo(TodoModel todo) {
        validationService.validatePastDate(todo);
        return port.createTodo(todo);
    }

    public List<TodoModel> getAllTodo() {
        return port.getAllTodos();
    }

    public TodoModel getTodoById(String id) {
        return port.getTodoById(id);
    }

    public TodoModel updateTodoById(String id, TodoModel model) {
        return port.updateTodoById(id, model);
    }
}
