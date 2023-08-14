package com.springprojects.service;

import com.springprojects.dto.TodoDto;
import org.springframework.stereotype.Service;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
}
