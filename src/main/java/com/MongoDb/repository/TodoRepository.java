package com.MongoDb.repository;

import com.MongoDb.model.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Repository
public interface TodoRepository extends MongoRepository<TodoDTO,String> {
    @Query("{'todo': ?0}")
    Optional<TodoDTO> findByTodo(String todo);
}
