package com.abreu.todoHexagonal.business.service;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ValidationService {

    public void validatePastDate(TodoModel date) {
        if (date.dueDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Date cannot be in the past");
        }
    }

    public void validateIfDataIDifferent(TodoModel model, TodoEntity entity) {
        validateIfTitleIsSame(model, entity);
        validateIfDescriptionIsSame(model, entity);
        validateIfDueDateIsSame(model, entity);
        validateIfPriorityIsSame(model, entity);
        validateIfStatusIsSame(model, entity);
    }

    public String validateIfTitleIsSame(TodoModel model, TodoEntity entity) {
        if (model.title() == null || model.title().isEmpty()) {
            return entity.getTitle();
        }
        if (model.title().equals(entity.getTitle())) {
            throw new BadRequestException("Title is the same");
        }
        return model.title();
    }

    public void validateIfDescriptionIsSame(TodoModel model, TodoEntity entity) {
        if (model.description().equals(entity.getDescription())) {
            throw new BadRequestException("Description is the same");
        }
    }

    public void validateIfDueDateIsSame(TodoModel model, TodoEntity entity) {
        if (model.dueDate().equals(entity.getDueDate())) {
            throw new BadRequestException("Due date is the same");
        }
    }

    public void validateIfPriorityIsSame(TodoModel model, TodoEntity entity) {
        if (Objects.equals(model.priority().toString(), entity.getPriority().toString())) {
            throw new BadRequestException("Priority is the same");
        }
    }

    public void validateIfStatusIsSame(TodoModel model, TodoEntity entity) {
        if (Objects.equals(entity.getStatus().toString(), model.status().toString()))
            throw new BadRequestException("Status is the same");
    }
}