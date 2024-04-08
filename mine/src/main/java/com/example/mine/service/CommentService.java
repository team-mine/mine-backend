package com.example.mine.service;

import com.example.mine.dto.CommentDto;
import com.example.mine.entity.CommentEntity;
import com.example.mine.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setContent(commentDto.getContent());
        commentEntity.setUsername(commentDto.getUsername());
        commentEntity.setBoardid(commentDto.getBoardid());
        commentEntity.setDatetime(LocalDateTime.parse(commentDto.getDatetime()));

        commentRepository.save(commentEntity);
    }

    public List<CommentEntity> getComments(Long boardId) {
        return commentRepository.findByboardid(boardId);
    }

    public void updateComment(Long commentId, CommentDto commentDto) {
        CommentEntity existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        existingComment.setContent(commentDto.getContent());
        commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
