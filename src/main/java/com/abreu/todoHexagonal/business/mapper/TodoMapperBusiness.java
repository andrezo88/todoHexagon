package com.abreu.todoHexagonal.business.mapper;

import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapperBusiness {

    TodoModel toModel(TodoEntity entity);

    TodoEntity toEntity(TodoModel model);
}
