package com.example.mine.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long commentid;         //댓글 식별 ID
    private Long boardid;           //댓글을 작성한 게시글 ID
    private String content;         //댓글 내용
    private String username;        //댓글 작성한 유저
    private String datetime;        //댓글 작성한 시간
    private String parentcomment;
}
