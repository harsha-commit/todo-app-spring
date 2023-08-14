package com.springprojects.service;

import com.springprojects.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodos();
}
