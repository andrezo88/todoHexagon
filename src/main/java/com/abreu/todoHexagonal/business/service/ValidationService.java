package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.StatusEnum;
import com.abreu.todoHexagonal.business.model.TodoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public Boolean validatePastDate(TodoModel date) {
        if (date.dueDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Date cannot be in the past");
        }
        return true;
    }

    public Boolean validateCompleted(TodoModel completed) {
        if (completed.completed()) {
            throw new BadRequestException("Todo is already completed");
        }
        return true;
    }

    public Boolean validateStatus(TodoModel status, TodoModel statusOld) {
        if (status.status() == statusOld.status()) {
            throw new BadRequestException("Status cannot be the same");
        }
        return true;
    }

    public Boolean validateIFIsStatusDone(TodoModel status) {
        if (status.status().equals(StatusEnum.DONE)) {
            throw new BadRequestException("Status cannot be DONE");
        }
        return true;
    }
}