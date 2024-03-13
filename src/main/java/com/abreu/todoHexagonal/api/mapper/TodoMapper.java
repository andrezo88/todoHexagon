package com.abreu.todoHexagonal.api.mapper;

import com.abreu.todoHexagonal.api.dto.TodoRequestDto;
import com.abreu.todoHexagonal.api.dto.TodoResponseDto;
import com.abreu.todoHexagonal.business.model.TodoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoModel toModel(TodoRequestDto dto);

    TodoResponseDto toResponse(TodoModel model);

}
