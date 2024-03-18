package com.abreu.todoHexagonal.business.mapper;

import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.ArrayList;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TodoMapperBusiness {

    @Named("isLateTodoDate")
    static boolean checkIfTodoIsLate(TodoEntity entity) {
        return entity.getDueDate().isBefore(LocalDate.now()) && !entity.getCompleted();
    }

    @Mapping(target = "isLate", source = "entity", qualifiedByName = "isLateTodoDate")
    TodoModel toModel(TodoEntity entity);

    @Mapping(target = "isLate", ignore = true)
    TodoModel toModelUpdate(TodoEntity entity);

    @Mapping(target = "status", source = "status", defaultValue = "TO_DO")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    TodoEntity toEntity(TodoModel model);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    default void toEntityUpdate(TodoModel model, @MappingTarget TodoEntity entity) throws BadRequestException {
        if (entity.getUpdatedAt() == null) {
            entity.setUpdatedAt(new ArrayList<>());
        }
        entity.getUpdatedAt().add(java.time.LocalDateTime.now());
    }
}
