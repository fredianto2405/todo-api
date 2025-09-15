package dev.freddxant.todo.service.impl;

import dev.freddxant.todo.model.Todo;
import dev.freddxant.todo.repository.TodoRepository;
import dev.freddxant.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    @Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @Override
    public Todo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found!"));
    }

    @Override
    public Todo save(Todo todo) {
        if (todo.getId() != null) {
            Todo todoExisting = getById(todo.getId());
            if (todoExisting == null) {
                throw new RuntimeException("Todo not found!");
            }

            todoExisting.setText(todo.getText());
            todoExisting.setCompleted(todo.getCompleted());
            return repository.save(todoExisting);
        }
        return repository.save(todo);
    }

    @Override
    public void delete(Long id) {
        Todo todo = getById(id);
        if (todo == null) {
            throw new RuntimeException("Todo not found!");
        }
        repository.deleteById(id);
    }
}
