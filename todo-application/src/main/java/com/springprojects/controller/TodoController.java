package com.springprojects.controller;

import com.springprojects.dto.TodoDto;
import com.springprojects.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // Build Add Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    // Build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todoDtos = todoService.getAllTodos();
        return new ResponseEntity<>(todoDtos, HttpStatus.OK);
    }

    // Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id){
        TodoDto updateTodoDto = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(updateTodoDto, HttpStatus.OK);
    }

    // Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo Deleted Successfully !!",HttpStatus.OK);
    }

    // Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/complete/{id}")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.completeTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Incomplete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/incomplete/{id}")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.incompleteTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
}
