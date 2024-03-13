package com.abreu.todoHexagonal.api.mapper;

import com.abreu.todoHexagonal.api.dto.TodoRequestDto;
import com.abreu.todoHexagonal.api.dto.TodoResponseDto;
import com.abreu.todoHexagonal.business.model.TodoModel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T17:11:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Microsoft)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoModel toModel(TodoRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;
        String description = null;
        LocalDate dueDate = null;
        String priority = null;
        Boolean status = null;

        title = dto.title();
        description = dto.description();
        dueDate = dto.dueDate();
        priority = dto.priority();
        status = dto.status();

        String id = null;

        TodoModel todoModel = new TodoModel( id, title, description, dueDate, priority, status );

        return todoModel;
    }

    @Override
    public TodoResponseDto toResponse(TodoModel model) {
        if ( model == null ) {
            return null;
        }

        TodoResponseDto.TodoResponseDtoBuilder todoResponseDto = TodoResponseDto.builder();

        todoResponseDto.id( model.id() );
        todoResponseDto.title( model.title() );
        todoResponseDto.description( model.description() );
        todoResponseDto.dueDate( model.dueDate() );
        todoResponseDto.priority( model.priority() );
        todoResponseDto.status( model.status() );

        return todoResponseDto.build();
    }
}
