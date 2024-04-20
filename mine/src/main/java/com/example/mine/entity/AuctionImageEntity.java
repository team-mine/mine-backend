package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auctionimage")
@Data
public class AuctionImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //게시글 이미지 아이디
    private Long auctionimageid;

    @Column(name = "auctionimagepath")                      //게시글 이미지 경로
    private String auctionimagepath;

    @ManyToOne
    @JoinColumn(name = "auctionid")                         //Join하기 위한 부모
    private AuctionEntity auctionentity;
}