package dev.freddxant.todo.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private Boolean success;
    private String message;
}
