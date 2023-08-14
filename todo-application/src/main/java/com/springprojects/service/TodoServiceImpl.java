package com.springprojects.service;

import com.springprojects.dto.TodoDto;
import com.springprojects.entity.Todo;
import com.springprojects.exception.ResourceNotFoundException;
import com.springprojects.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // Convert TodoDto into Todo JPA Entity
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Save JPA Entity into Database
        Todo savedTodo = todoRepository.save(todo);

        // Convert savedTodo into TodoDto
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo not found with ID: " + id)
        );
        return modelMapper.map(todo, TodoDto.class);
    }
}
