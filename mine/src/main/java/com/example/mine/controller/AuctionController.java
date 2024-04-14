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
                                               @RequestParam(value = "auctionendtime", required = false) String auctionendtime,
                                               @RequestParam(value = "auctiondirectbid", required = false) String auctiondirectbid){
        try{
            AuctionDto auctiondto = new AuctionDto();
            auctiondto.setAuctionimage(auctionimages);
            auctiondto.setAuctiontitle(auctiontitle);
            auctiondto.setAuctioncontent(auctioncontent);
            auctiondto.setAuctionuser(auctionuser);
            auctiondto.setAuctioncategory(auctioncategory);
            auctiondto.setAuctionprice(auctionprice);
            auctiondto.setAuctionendtime(auctionendtime);
            auctiondto.setAuctiondirectbid(auctiondirectbid);
            auctiondto.setAuctionbidprice(auctionprice);

            auctionservice.saveAuction(auctiondto);
            return ResponseEntity.ok("글 작성 완료!");
        }catch(Exception e){
            e.printStackTrace();
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

    @GetMapping("/auctionboardread")
    public ResponseEntity<AuctionDto> auctionboardread(@RequestParam(value = "auctionid") Long auctionid){
        try{
            AuctionDto auctionDto = new AuctionDto();

            auctionDto.setAuctionid(auctionid);

            AuctionDto auctionlist = auctionservice.getBoardAuction(auctionDto);
            return ResponseEntity.ok(auctionlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/auctionbidprice")
    public ResponseEntity<String> auctionbidprice(@RequestParam(value = "auctionid", required = false) Long auctionid,
                                                  @RequestParam(value = "auctionbidprice", required = false) String auctionbidprice,
                                                  @RequestParam(value = "auctionbidder", required = false) String auctionbidder,
                                                  @RequestParam(value = "auctionbidsnum") Long auctionbidsnum){

        try{
            AuctionDto auctionDto = new AuctionDto();

            auctionDto.setAuctionid(auctionid);
            auctionDto.setAuctionbidprice(auctionbidprice);
            auctionDto.setAuctionbidder(auctionbidder);
            auctionDto.setAuctionbidsnum(auctionbidsnum);

            auctionservice.updateAuctionBidPrice(auctionDto);

            return ResponseEntity.ok("경매입찰가 업데이트 완료!");
            }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 못한 오류입니다");
        }
    }

    @PutMapping("/auctionupdate")
    public ResponseEntity<String> auctionupdate(@RequestParam(value = "auctionid", required = false) Long auctionid,
                                                @RequestParam(value = "auctioncategory", required = false) String auctioncategory,
                                                @RequestParam(value = "acutiontitle", required = false) String auctiontitle,
                                                @RequestParam(value = "auctioncontent", required = false) String auctioncontent,
                                                @RequestParam(value = "auctionimages", required = false) List<MultipartFile> auctionimages,
                                                @RequestParam(value = "auctionuser", required = false) String auctionuser,
                                                @RequestParam(value = "auctionoldimg", required = false) List<String> auctionoldimg){
        try{
            AuctionDto auctionDto = new AuctionDto();

            auctionDto.setAuctionid(auctionid);
            auctionDto.setAuctioncategory(auctioncategory);
            auctionDto.setAuctiontitle(auctiontitle);
            auctionDto.setAuctioncontent(auctioncontent);
            auctionDto.setAuctionimage(auctionimages);
            auctionDto.setAuctionuser(auctionuser);
            auctionDto.setAuctionoldimg(auctionoldimg);

            String auctionresponse = auctionservice.updateAuction(auctionDto);

            return ResponseEntity.ok(auctionresponse);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 못한 오류입니다");
        }
    }

    @DeleteMapping("/auctiondelete")
    public ResponseEntity<String> auctiondelete(@RequestParam(value = "auctionid", required = false) Long auctionid,
                                                @RequestParam(value = "auctionuser", required = false) String auctionuser){
        try{
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctionid(auctionid);
            auctionDto.setAuctionuser(auctionuser);

            String auctionresponse = auctionservice.auctiondelete(auctionDto);
            return ResponseEntity.ok(auctionresponse);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 못한 오류입니다");
        }
    }
}
