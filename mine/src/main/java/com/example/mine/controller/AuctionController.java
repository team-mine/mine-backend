package com.example.mine.controller;

import com.example.mine.dto.AuctionDto;
import com.example.mine.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AuctionController {
    private final AuctionService auctionservice;

    @Autowired
    public AuctionController(AuctionService auctionservice) {
        this.auctionservice = auctionservice;
    }

    @PostMapping("/auctionwrite")
    public ResponseEntity<String> auctionwrite(@RequestParam(value = "auctionimages", required = false) List<MultipartFile> auctionimages,
                                               @RequestParam(value = "auctiontitle", required = false) String auctiontitle,
                                               @RequestParam(value = "auctioncontent", required = false) String auctioncontent,
                                               @RequestParam(value = "auctionuser", required = false) String auctionuser,
                                               @RequestParam(value = "auctioncategory", required = false) String auctioncategory,
                                               @RequestParam(value = "auctionprice", required = false) String auctionprice,
                                               @RequestParam(value = "auctionendtime", required = false) String auctionendtime){
        try{
            AuctionDto auctiondto = new AuctionDto();
            auctiondto.setAuctionimage(auctionimages);
            auctiondto.setAuctiontitle(auctiontitle);
            auctiondto.setAuctioncontent(auctioncontent);
            auctiondto.setAuctionuser(auctionuser);
            auctiondto.setAuctioncategory(auctioncategory);
            auctiondto.setAuctionprice(auctionprice);
            auctiondto.setAuctionendtime(auctionendtime);

            auctionservice.saveAuction(auctiondto);
            return ResponseEntity.ok("글 작성 완료!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 못한 오류입니다");
        }
    }

    @GetMapping("/auctionread")
    public ResponseEntity<List<AuctionDto>> auctionread() {
        try {
            List<AuctionDto> auctionlist = auctionservice.getAuction();
            return ResponseEntity.ok(auctionlist);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
