package com.example.mine.service;

import com.example.mine.dto.CommentDto;
import com.example.mine.entity.CommentEntity;
import com.example.mine.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        commentEntity.setParentcomment(commentDto.getParentcomment());

        commentRepository.save(commentEntity);
    }

    public List<CommentEntity> getComments(Long boardId) {
        return commentRepository.findByboardid(boardId);
    }

    public String updateComment(Long commentId, CommentDto commentDto) {
        try {
            Optional<CommentEntity> commentOptional = commentRepository.findById(commentDto.getCommentid());

            if (commentOptional.isPresent()) {
                CommentEntity existingComment = commentOptional.get();

                if (!existingComment.getUsername().equals(commentDto.getUsername())) {
                    return "댓글의 작성자가 아닙니다. 삭제할 수 없습니다.";
                }
                existingComment.setContent(commentDto.getContent());

                commentRepository.save(existingComment);
                return "댓글 수정완료";
            } else {
                return "해당하는 댓글이 존재하지 않습니다.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "댓글 수정 실패";
        }
    }

    public String deleteComment(CommentDto commentDto) {
        try {
            Optional<CommentEntity> commentOptional = commentRepository.findById(commentDto.getCommentid());

            if (commentOptional.isPresent()) {
                CommentEntity existingComment = commentOptional.get();

                if (!existingComment.getUsername().equals(commentDto.getUsername())) {
                    return "댓글의 작성자가 아닙니다. 삭제할 수 없습니다.";
                }

                commentRepository.deleteById(commentDto.getCommentid());
                return "댓글 삭제완료";
            } else {
                return "해당하는 댓글이 존재하지 않습니다.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "댓글 삭제 실패";
        }
    }
}
