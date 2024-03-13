package com.abreu.todoHexagonal.infrasctruture.repository;

import com.abreu.todoHexagonal.infrasctruture.repository.entity.TodoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoEntity, String> {
}
