package com.learn.basic.controller;

import com.learn.basic.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/todos")
public class TodoController {

   // private final TodoRepository todoRepository;

   // @Autowired
  //  public TodoController(TodoRepository todoRepository) {
  //      this.todoRepository = todoRepository;
 //   }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody Todo todo) {
       //Todo savedTodo = todoRepository.save(todo);
        return new ResponseEntity<>("backendworks", HttpStatus.CREATED);
    }
}
