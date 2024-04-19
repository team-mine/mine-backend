package com.example.mine.service;

import com.example.mine.dto.UserDto;
import com.example.mine.entity.AuctionEntity;
import com.example.mine.entity.UserEntity;
import com.example.mine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public String saveuser(UserDto userDto){
        try {
            String user = userDto.getUser();
            String username = userDto.getUsername();



            Optional<UserEntity> userOptional = userRepository.findByUser(user);

            if (userOptional.isPresent()) {
                return "이미 존재하는 사용자입니다.";
            }

            UserEntity userEntity = new UserEntity();
            userEntity.setUser(user);
            userEntity.setUsername(username);

            userRepository.save(userEntity);

            return "사용자 추가 완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "사용자 추가 실패";
        }
    }

    public UserDto readuser(UserDto userDto){
        UserDto userdtos = new UserDto();
        try{
            String user = userDto.getUser();

            Optional<UserEntity> userOptional = userRepository.findByUser(user);

            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();

                userdtos.setUserid(userEntity.getUserid());
                userdtos.setUsername(userEntity.getUsername());
                userdtos.setBidid(userEntity.getBidid());
                userdtos.setWriteid(userEntity.getWriteid());
                userdtos.setScrapid(userEntity.getScrapid());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return userdtos;
    }

    public String scrapuser(UserDto userDto){
        try{
            Optional<UserEntity> userOptional = userRepository.findByUser(userDto.getUser());

            if (userOptional.isPresent()) {
                Optional<UserEntity> userscrapOptional = userRepository.findByScrapid(userDto.getScrapid());
                if(userscrapOptional.isPresent()){
                    return("이미 스크랩중입니다.");
                }else{
                    UserEntity userEntity = userOptional.get();
                    userEntity.setScrapid(userDto.getScrapid());

                    userRepository.save(userEntity);
                }

            }else{
             return("유저가 존재하지 않습니다!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return("스크랩 완료!");
    }

    public String unscrapuser(UserDto userDto){
        try{
            Optional<UserEntity> userOptional = userRepository.findByUser(userDto.getUser());

            if (userOptional.isPresent()) {
                Optional<UserEntity> userscrapOptional = userRepository.findByScrapid(userDto.getScrapid());
                if(userscrapOptional.isPresent()){
                    UserEntity userEntity = userOptional.get();
                    userEntity.setScrapid(null);

                    userRepository.save(userEntity);
                }else{
                    return("스크랩 중이지 않습니다.");
                }

            }else{
                return("유저가 존재하지 않습니다!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return("스크랩 취소 완료!");
    }
}
