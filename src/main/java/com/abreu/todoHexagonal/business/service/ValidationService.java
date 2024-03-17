package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.mapper.TodoMapperBusiness;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    private final TodoRepository repository;
    private final TodoMapperBusiness mapper;

    public ValidationService(TodoRepository repository, TodoMapperBusiness mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void validatePastDate(TodoModel date) {
        if (date.dueDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Date cannot be in the past");
        }
    }
}
