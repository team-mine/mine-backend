package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;

    @Column(name = "boardid")
    private Long boardid;

    @Column(name = "content")
    private String content;

    @Column(name = "username")
    private String username;

    @Column(name = "datetime")
    private LocalDateTime datetime;
}