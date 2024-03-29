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
    private String auctioncategori;
    private Long auctionprice;
    private Long auctionendtime;
    private List<MultipartFile> auctionimage;
    private List<String> auctionimageurl;
}
