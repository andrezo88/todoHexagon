package com.abreu.todoHexagonal.business.service.port;

import com.abreu.todoHexagonal.business.model.TodoModel;

import java.util.List;

public interface TodoPort {


    TodoModel createTodo(TodoModel dto);

    List<TodoModel> getAllTodos();

    TodoModel getTodoById(String id);

    TodoModel updateTodoById(String id, TodoModel model);
}
