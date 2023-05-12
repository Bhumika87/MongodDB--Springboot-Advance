package com.MongoDb.service;

import com.MongoDb.exception.TodoCollectionException;
import com.MongoDb.model.TodoDTO;
import com.MongoDb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private  TodoRepository todoRepository;
    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = todoRepository.findAll();
        if (todos.size() > 0) {
            return todos;
        }else {
            return new ArrayList<TodoDTO>();
        }
    }

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException{
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if (!todoOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else {
            return todoOptional.get();
        }
    }

    @Override
    public void createTodo(TodoDTO todo) throws TodoCollectionException{
        Optional<TodoDTO> todoOptional= todoRepository.findByTodo(todo.getTodo());
        if(todoOptional.isPresent())
        {
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }
        else
        {
            todo.setCreatedDate(new Date(System.currentTimeMillis()));
            todoRepository.save(todo);
        }

    }
    @Override
    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException{
        Optional<TodoDTO> todoWithId = todoRepository.findById(id);
        Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todo.getTodo());
        if(todoWithId.isPresent())
        {
            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id))
            {

                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }
            TodoDTO todoToUpdate=todoWithId.get();

            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.isCompleted());
            todoToUpdate.setUpdatedDate(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToUpdate);
        }
        else
        {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }
    @Override
    public void deleteTodoById(String id) throws TodoCollectionException{
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if(!todoOptional.isPresent())
        {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        else
        {
            todoRepository.deleteById(id);
        }

    }

}
