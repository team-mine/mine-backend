package com.example.mine.controller;

import com.example.mine.dto.AuctionDto;
import com.example.mine.dto.CommentDto;
import com.example.mine.entity.CommentEntity;
import com.example.mine.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/write")
    public ResponseEntity<String> write_comment(@RequestParam(value = "boardid")Long boardid,
                                                @RequestParam(value = "content")String content,
                                                @RequestParam(value = "username")String username) {
        try {
            CommentDto commentDto = new CommentDto();
            commentDto.setBoardid(boardid);
            commentDto.setContent(content);
            commentDto.setUsername(username);
            commentDto.setDatetime(LocalDateTime.now().toString());
            commentService.addComment(commentDto);
            return ResponseEntity.ok("댓글 작성 완료!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 못한 오류입니다");
        }
    }

    @PostMapping("/comment/read")
    public ResponseEntity<List<CommentEntity>> getComments(@RequestParam (value = "boardid")Long boardid){
        try {
            List<CommentEntity> commentlist = commentService.getComments(boardid);
            return ResponseEntity.ok(commentlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/comment/update/{commentid}")
    public String updateComment(@PathVariable (name = "commentid") Long commentid,
                                @RequestBody CommentDto commentDto) {
        commentService.updateComment(commentid, commentDto);
        return "댓글이 성공적으로 수정되었습니다.";
    }

    @DeleteMapping("/comment/delete/{commentid}")
    public String deleteComment(@PathVariable(name = "commentid") Long commentid,
                                @RequestParam(name = "username") String username){
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentid(commentid);
        commentDto.setUsername(username);

        commentService.deleteComment(commentDto);
        return "댓글이 성공적으로 삭제되었습니다.";
    }
}
