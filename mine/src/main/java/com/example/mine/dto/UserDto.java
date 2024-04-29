package com.example.mine.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long userid;            //유저 식별 번호
    private String user;            //유저 이메일
    private String username;        //유저 닉네임
    private String bidid;             //입찰한 게시글 ID
    private String writeid;           //작성한 게시글 ID
    private String scrapid;           //스크랩한 게시글 ID
    private List<String> scrapids;    //리스트 형식의 스크랩한 게시글 ID
    private List<String> bidids;
    private List<String> writeids;
}
