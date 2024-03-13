package com.abreu.todoHexagonal.business.mapper;

import com.abreu.todoHexagonal.business.model.TodoModel;
import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T17:11:17-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Microsoft)"
)
@Component
public class TodoMapperBusinessImpl implements TodoMapperBusiness {

    @Override
    public TodoModel toModel(TodoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        String title = null;
        String description = null;
        LocalDate dueDate = null;
        String priority = null;
        Boolean status = null;

        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        dueDate = entity.getDueDate();
        priority = entity.getPriority();
        status = entity.getStatus();

        TodoModel todoModel = new TodoModel( id, title, description, dueDate, priority, status );

        return todoModel;
    }

    @Override
    public TodoEntity toEntity(TodoModel model) {
        if ( model == null ) {
            return null;
        }

        TodoEntity.TodoEntityBuilder todoEntity = TodoEntity.builder();

        todoEntity.id( model.id() );
        todoEntity.title( model.title() );
        todoEntity.description( model.description() );
        todoEntity.dueDate( model.dueDate() );
        todoEntity.priority( model.priority() );
        todoEntity.status( model.status() );

        return todoEntity.build();
    }
}
