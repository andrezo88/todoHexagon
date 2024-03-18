package com.abreu.todoHexagonal.api.mapper;

import com.abreu.todoHexagonal.api.dto.TodoRequestDto;
import com.abreu.todoHexagonal.api.dto.TodoResponseDto;
import com.abreu.todoHexagonal.api.dto.TodoUpdateDto;
import com.abreu.todoHexagonal.business.model.TodoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mapping(target = "id", ignore = true)
    TodoModel toModel(TodoRequestDto dto);

    TodoResponseDto toResponse(TodoModel model);

    TodoModel toModelUpdate(TodoUpdateDto dto);

}
