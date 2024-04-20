package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "auction")
@Data
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionid;

    @Column(name = "auctiontitle")                                                          //게시글 제목
    private String auctiontitle;

    @Column(name = "auctioncontent")                                                        //게시글 본문
    private String auctioncontent;

    @Column(name = "auctionuser")                                                           //게시글 작성자 이메일
    private String auctionuser;

    @Column(name = "auctionusername")                                                       //게시글 작성자 닉네임
    private  String auctionusername;

    @Column(name = "auctiontime")                                                           //게시글 작성 시간
    private String auctiontime;

    @Column(name = "auctioncategory")                                                       //게시글 카테고리
    private String auctioncategory;

    @Column(name = "auctionprice")                                                          //게시글 경매 시작 가격
    private String auctionprice;

    @Column(name = "auctionendtime")                                                        //경매 끝나는 시간
    private String auctionendtime;

    @OneToMany(mappedBy = "auctionentity", cascade = CascadeType.ALL, orphanRemoval = true) //경매 이미지를 담기 위하여 AuctionImage 테이블과 연동
    private List<AuctionImageEntity> auctionimages;

    @Column(name = "auctionbidder")                                                         //경매 입찰자
    private String auctionbidder;

    @Column(name = "auctiondirectbid")                                                      //경매 즉시 입찰가격
    private String auctiondirectbid;

    @Column(name = "auctionbidprice")                                                       //경매 입찰 가격
    private String auctionbidprice;

    @Column(name = "auctionbidsnum")                                                        //경매 입찰 횟수
    private Long auctionbidsnum;
}
