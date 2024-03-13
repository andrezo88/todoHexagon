package com.abreu.todoHexagonal.infrasctruture.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "todo")
@Builder
@AllArgsConstructor
@Data

public class TodoEntity {
    @Id
    private String id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private Priority priority;

    private Boolean status;
}
