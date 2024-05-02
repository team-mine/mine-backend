package com.example.mine.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AuctionDto {
    private Long auctionid;                             //게시물 아이디
    private String auctiontitle;                        //게시물 제목
    private String auctioncontent;                      //게시물 본문
    private String auctionuser;                         //게시글 작성자 이메일
    private String auctionusername;                     //게시글 작성자 닉네임
    private String auctiontime;                         //게시글 작성 시간
    private String auctioncategory;                     //게시글 카테고리
    private String auctionprice;                        //게시글 경매 시작가격
    private String auctionendtime;                      //게시글 경매 끝나는 시간
    private List<MultipartFile> auctionimage;           //게시글 이미지
    private List<String> auctionoldimg;                 //수정시에 이전에 사용했던 이미지 경로를 담기 위한 변수
    private List<String> auctionimageurl;               //이미지 경로를 반환하기 위한 변수
    private String auctionbidder;                       //경매 입찰자
    private Long auctiondirectbid;                    //경매 즉시 구매 가격
    private String auctionbidprice;                     //경매 입찰가격
    private String auctionfirsturl;                     //이미지 배열의 첫번째 경로만 담은 변수
    private Long auctionbidsnum;                        //경매 입찰 횟수
    private String auctionkeyword;                      //본문과 제목을 동시에 검색할 때 사용되는 변수
    private boolean auctioncomplete;                    //경매완료 여부
}
