package com.example.lesson62.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Integer id;
    private String commentText;
    private LocalDateTime commentTime;
    private Integer publication_id;
    private Integer user_id;
}
