package service;

import models.Comment;
import repositories.CommentRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository = new CommentRepository();

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }

    public List<Comment> findAll() {
        return commentRepository.finAll();
    }
}
