package com.example.lesson62.dto;

import com.example.lesson62.entity.Publication;
import com.example.lesson62.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeItDto {
    private Integer user_id;
    private Integer publication_id;
    private User whoMarkedThe;
    private Publication whatWasTheMark;
    private LocalDateTime likeTime;
}
