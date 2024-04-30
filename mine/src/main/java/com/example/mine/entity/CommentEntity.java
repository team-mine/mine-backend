package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //댓글 식별 ID
    private Long commentid;

    @Column(name = "boardid")                                   //댓글을 작성한 게시글 ID
    private Long boardid;

    @Column(name = "content")                                   //댓글 내용
    private String content;

    @Column(name = "user")                                  //댓글 작성자
    private String user;

    @Column(name = "username")                                  //댓글 작성자
    private String username;

    @Column(name = "datetime")                                  //댓글 작성 시간
    private LocalDateTime datetime;

    @Column(name = "parentcomment")                             //부모 댓글
    private String parentcomment;
}