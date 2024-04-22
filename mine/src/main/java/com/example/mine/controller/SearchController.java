package com.example.mine.controller;

import com.example.mine.dto.AuctionDto;
import com.example.mine.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchservice;
    @Autowired
    public SearchController(SearchService searchservice) {
        this.searchservice = searchservice;
    }

    @GetMapping("/categorysearch")
    public ResponseEntity<List<AuctionDto>> categorysearch(@RequestParam(value = "auctioncategory") String auctioncategory,
                                                           @RequestParam(value = "auctionuser", required = false) String auctionuser,
                                                           @RequestParam(value = "auctiontitle", required = false) String auctiontitle,
                                                           @RequestParam(value = "auctioncontent", required = false) String auctioncontent,
                                                           @RequestParam(value = "auctionkeyword", required = false) String auctionkeyword){
        try{
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctioncategory(auctioncategory);
            auctionDto.setAuctionuser(auctionuser);
            auctionDto.setAuctiontitle(auctiontitle);
            auctionDto.setAuctioncontent(auctioncontent);
            auctionDto.setAuctionkeyword(auctionkeyword);

            List<AuctionDto> auctionlist = searchservice.Categoryget(auctionDto);

            return ResponseEntity.ok(auctionlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/usernamesearch")
    public ResponseEntity<List<AuctionDto>> usernamesaerch(@RequestParam(value = "auctionuser")String auctionuser,
                                                           @RequestParam(value = "auctioncategory", required = false)String auctioncategory){
        try {
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctionuser(auctionuser);
            auctionDto.setAuctioncategory(auctioncategory);

            List<AuctionDto> auctionlist = searchservice.usernameget(auctionDto);

            System.out.println("auctionuser : " + auctionuser);
            return ResponseEntity.ok(auctionlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<AuctionDto>> titlesearch(@RequestParam(value = "auctiontitle")String auctiontitle,
                                                        @RequestParam(value = "auctioncategory", required = false)String auctioncategory){
        try{
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctiontitle(auctiontitle);
            auctionDto.setAuctioncategory(auctioncategory);

            List<AuctionDto> auctionlist = searchservice.titleget(auctionDto);
            System.out.println(auctiontitle);
            return ResponseEntity.ok(auctionlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/contentsearch")
    public ResponseEntity<List<AuctionDto>> contentsaerch(@RequestParam(value = "auctioncontent")String auctioncontent,
                                                          @RequestParam(value = "auctioncategory", required = false)String auctioncategory){
        try{
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctioncontent(auctioncontent);
            auctionDto.setAuctioncategory(auctioncategory);

            List<AuctionDto> auctionlist = searchservice.contentget(auctionDto);
            return ResponseEntity.ok(auctionlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/bothsearch")
    public ResponseEntity<List<AuctionDto>> bothsaerch(@RequestParam(value = "auctionkeyword")String auctionkeyword,
                                                       @RequestParam(value = "auctioncategory", required = false)String auctioncategory){
        try{
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctionkeyword(auctionkeyword);
            auctionDto.setAuctioncategory(auctioncategory);

            List<AuctionDto> auctionlist = searchservice.content_and_titleget(auctionDto);
            return ResponseEntity.ok(auctionlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
