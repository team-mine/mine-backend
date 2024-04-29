package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "userbidid")
@Data
public class BididEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)       //입찰 글 구별 ID
    private Long idbidid;

    @Column(name = "bidid")                                 //입찰한 게시글 ID
    private String bidid;

    @ManyToOne
    @JoinColumn(name = "userid")                              //Join하기 위한 부모
    private UserEntity userentity;
}