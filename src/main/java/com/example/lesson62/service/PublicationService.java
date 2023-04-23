package com.example.lesson62.service;

import com.example.lesson62.dao.LikeItDao;
import com.example.lesson62.dao.PublicationDao;
import com.example.lesson62.entity.Publication;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicationService {
    @Autowired
    private PublicationDao publicationDao;

    private LikeItDao likeItDao;

    public List<Publication> getPublicationOfOtherUsers(String email) {
        return publicationDao.getPublicationOfOtherUsers(email);
    }

    public List<Publication> selectAllPublications() {
        return publicationDao.selectAllPublications();
    }

    public List<Publication> getPublicationById(Long userId) {
        return publicationDao.getPublicationById(userId);
    }

    public boolean deletePublicationById(Long id) {
        publicationDao.deleteById(id);
        return true;
    }

    public boolean likePublication(Long id, Long postId) {
        return likeItDao.likePublication(id, postId);
    }

    public boolean deleteComment(Long commentId) {
        publicationDao.deleteCommentById(commentId);
        return true;
    }

    public void addPublication(String fileName, String description, Integer userId) {
        publicationDao.save(fileName,description,userId);
    }

    public Long getPostLastId() {
        return publicationDao.getPostLastId();
    }
}
