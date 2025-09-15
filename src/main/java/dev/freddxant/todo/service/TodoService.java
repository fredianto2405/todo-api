package dev.freddxant.todo.service;

import dev.freddxant.todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAll();
    Todo getById(Long id);
    Todo save(Todo todo);
    void delete(Long id);
}
