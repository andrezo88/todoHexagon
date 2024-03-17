package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.TodoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public void validatePastDate(TodoModel date) {
        if (date.dueDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Date cannot be in the past");
        }
    }
}
