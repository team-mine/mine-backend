package com.example.mine.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long commentid;
    private Long boardid;
    private String content;
    private String username;
    private String datetime;

}
