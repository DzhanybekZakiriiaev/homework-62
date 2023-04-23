package com.example.lesson62.dto;

import com.example.lesson62.entity.Publication;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDto {
    private Integer id;
    private String imageLink;
    private String description;
    private LocalDateTime publicationTime;
    private Integer likes;
    private Integer user_id;

    public static PublicationDto from(Publication publication) {
        return builder()
                .imageLink(publication.getImageLink())
                .description(publication.getDescription())
                .publicationTime(publication.getPublicationTime())
                .likes(publication.getLikes())
                .user_id(publication.getUser_id())
                .build();
    }
}
