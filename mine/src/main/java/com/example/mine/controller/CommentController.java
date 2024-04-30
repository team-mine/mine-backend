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
    public ResponseEntity<String> write_comment(@RequestParam(value = "boardid")String boardid,
                                                @RequestParam(value = "content")String content,
                                                @RequestParam(value = "user")String user,
                                                @RequestParam(value = "username")String username,
                                                @RequestParam(value = "parentcomment", required = false)String parentcomment) {
        try {
            Long lboardid = Long.parseLong(boardid);
            CommentDto commentDto = new CommentDto();
            commentDto.setBoardid(lboardid);
            commentDto.setContent(content);
            commentDto.setUser(user);
            commentDto.setUsername(username);
            commentDto.setDatetime(LocalDateTime.now().toString());
            commentDto.setParentcomment(parentcomment);
            commentService.addComment(commentDto);
            return ResponseEntity.ok("댓글 작성 완료!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 못한 오류입니다");
        }
    }

    @PostMapping("/comment/read")
    public ResponseEntity<List<CommentEntity>> getComments(@RequestParam (value = "boardid")String boardid){
        try {
            Long lboardid = Long.parseLong(boardid);
            List<CommentEntity> commentlist = commentService.getComments(lboardid);
            return ResponseEntity.ok(commentlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/comment/update/{commentid}")
    public String updateComment(@PathVariable (name = "commentid") String commentid,
                                @RequestBody CommentDto commentDto) {
        long lcommentid = Long.parseLong(commentid);
        commentService.updateComment(lcommentid, commentDto);
        return "댓글이 성공적으로 수정되었습니다.";
    }

        @DeleteMapping("/comment/delete/{commentid}")
    public String deleteComment(@PathVariable(name = "commentid") String commentid,
                                @RequestParam(name = "username") String username){
            long lcommentid = Long.parseLong(commentid);
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentid(lcommentid);
        commentDto.setUsername(username);

        commentService.deleteComment(commentDto);
        return "댓글이 성공적으로 삭제되었습니다.";
    }
}
