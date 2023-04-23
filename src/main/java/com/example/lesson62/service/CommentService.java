package com.example.lesson62.service;

import com.example.lesson62.dao.CommentDao;
import com.example.lesson62.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;
    public List<CommentDto> showCommentsFromPublication() {
        return commentDao.getCommentsFromPublication();
    }

    public void addComment(Long userId, Long postId, String text) {
        commentDao.addComment(userId, postId, text);
    }
}
