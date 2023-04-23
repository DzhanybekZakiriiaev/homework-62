package com.example.lesson62.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication {
    private Integer id;
    private String imageLink;
    private String description;
    private LocalDateTime publicationTime;
    private Integer likes;
    private Integer user_id;
}
