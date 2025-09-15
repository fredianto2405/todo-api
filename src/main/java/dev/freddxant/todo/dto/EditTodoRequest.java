package dev.freddxant.todo.dto;

import lombok.Data;

@Data
public class EditTodoRequest {
    private String text;
    private Boolean completed;
}
