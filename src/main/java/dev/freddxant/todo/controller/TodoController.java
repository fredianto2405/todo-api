package dev.freddxant.todo.controller;

import dev.freddxant.todo.common.dto.MessageDataResponse;
import dev.freddxant.todo.common.dto.MessageResponse;
import dev.freddxant.todo.dto.AddTodoRequest;
import dev.freddxant.todo.dto.EditTodoRequest;
import dev.freddxant.todo.model.Todo;
import dev.freddxant.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Todo> todos = service.getAll();
            MessageDataResponse res = MessageDataResponse.builder()
                    .success(true)
                    .message("Todo list retrieved successfully!")
                    .data(todos)
                    .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            MessageResponse res = MessageResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Todo todo = service.getById(id);
            MessageDataResponse res = MessageDataResponse.builder()
                    .success(true)
                    .message("Todo retrieved successfully!")
                    .data(todo)
                    .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            MessageResponse res = MessageResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddTodoRequest request) {
        try {
            Todo todo = service.save(Todo.builder()
                    .text(request.getText())
                    .completed(false)
                    .build());
            MessageDataResponse res = MessageDataResponse.builder()
                    .success(true)
                    .message("Todo added successfully!")
                    .data(todo)
                    .build();
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            MessageResponse res = MessageResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody EditTodoRequest request) {
        try {
            Todo todo = service.save(Todo.builder()
                    .id(id)
                    .text(request.getText())
                    .completed(request.getCompleted())
                    .build());
            MessageDataResponse res = MessageDataResponse.builder()
                    .success(true)
                    .message("Todo edited successfully!")
                    .data(todo)
                    .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            MessageResponse res = MessageResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            MessageResponse res = MessageResponse.builder()
                    .success(true)
                    .message("Todo removed successfully!")
                    .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            MessageResponse res = MessageResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
