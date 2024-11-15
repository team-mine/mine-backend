package com.example.mine.controller;

import com.example.mine.dto.UserDto;
import com.example.mine.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userservice;
    private UserController(UserService userservice){this.userservice = userservice;}
    @PostMapping("/saveuser")
    public ResponseEntity<String> saveuser(@RequestParam(value = "user") String user,
                                           @RequestParam(value = "username") String username){
        try{
            UserDto userDto = new UserDto();
            userDto.setUser(user);
            userDto.setUsername(username);
            userservice.saveuser(userDto);

            return ResponseEntity.ok("유저정보 저장 성공");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("505 예기치 않은 오류입니다.");
        }
    }

    @PostMapping("/readuser")
    public ResponseEntity<UserDto> readuser(@RequestParam(value = "user") String user){
        try{
            UserDto userDto = new UserDto();
            userDto.setUser(user);

            UserDto userlist = userservice.readuser(userDto);


            return ResponseEntity.ok(userlist);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/scrap")
    public ResponseEntity<String> scrap(@RequestParam(value = "user") String user,
                                        @RequestParam(value = "auctionid") String auctionid){
        try {
            UserDto userDto = new UserDto();
            userDto.setUser(user);
            userDto.setScrapid(auctionid);

            userservice.scrapuser(userDto);

            return ResponseEntity.ok("스크랩 성공!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/unscrap")
    public ResponseEntity<String> unscrap(@RequestParam(value = "user") String user,
                                        @RequestParam(value = "auctionid") String auctionid){
        try {
            UserDto userDto = new UserDto();
            userDto.setUser(user);
            userDto.setScrapid(auctionid);

            userservice.unscrapuser(userDto);

            return ResponseEntity.ok("스크랩 취소 성공!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
