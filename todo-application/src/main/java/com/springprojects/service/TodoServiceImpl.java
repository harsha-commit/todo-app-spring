package com.springprojects.service;

import com.springprojects.dto.TodoDto;
import com.springprojects.entity.Todo;
import com.springprojects.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // Convert TodoDto into Todo JPA Entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        // Save JPA Entity into Database
        Todo savedTodo = todoRepository.save(todo);

        // Convert savedTodo into TodoDto
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());

        return savedTodoDto;
    }
}
