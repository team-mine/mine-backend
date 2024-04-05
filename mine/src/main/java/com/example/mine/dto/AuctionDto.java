package com.example.mine.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AuctionDto {
    private Long auctionid;
    private String auctiontitle;
    private String auctioncontent;
    private String auctionuser;
    private String auctiontime;
    private String auctioncategory;
    private String auctionprice;
    private String auctionendtime;
    private List<MultipartFile> auctionimage;
    private List<String> auctionimageurl;
    private String auctionbidder;
    private String auctiondirectbid;
    private String auctionbidprice;
    private String auctionfirsturl;
}
