package com.example.mine.service;

import com.example.mine.dto.UserDto;
import com.example.mine.entity.*;
import com.example.mine.repository.ScrapRepository;
import com.example.mine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;

    @Autowired
    public UserService(UserRepository userRepository, ScrapRepository scrapRepository){
        this.userRepository = userRepository;
        this.scrapRepository = scrapRepository;
    }

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
                userdtos.setUser(userEntity.getUser());
                userdtos.setUsername(userEntity.getUsername());

                List<String> scrapIds = userEntity.getScraps().stream()
                        .map(ScrapEntity::getScrapid)
                        .collect(Collectors.toList());
                userdtos.setScrapids(scrapIds);

                List<String> bidids = userEntity.getBidid().stream()
                        .map(BididEntity::getBidid)
                        .collect(Collectors.toList());
                userdtos.setBidids(bidids);

                List<String> writeids = userEntity.getWriteid().stream()
                        .map(WriteidEntity::getWriteid)
                        .collect(Collectors.toList());
                userdtos.setWriteids(writeids);
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
                Optional<ScrapEntity> scrapOptional = scrapRepository.findByScrapid(userDto.getScrapid());
                if(scrapOptional.isPresent()){
                    return("이미 스크랩중입니다.");
                }else{
                    UserEntity newuser = userOptional.get();
                    ScrapEntity newScrap = new ScrapEntity();
                    newScrap.setScrapid(userDto.getScrapid());
                    newScrap.setUserentity(newuser);

                    scrapRepository.save(newScrap);
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
                Optional<ScrapEntity> scrapOptional = scrapRepository.findByScrapid(userDto.getScrapid());

                if(scrapOptional.isPresent()){
                    UserEntity userEntity = userOptional.get();

                    ScrapEntity scrapToRemove = scrapOptional.get();
                    userEntity.getScraps().remove(scrapToRemove);

                    userRepository.save(userEntity);

                    return "스크랩 취소 완료!";
                } else {
                    return "스크랩 정보가 없습니다!";
                }

            } else {
                return "유저가 존재하지 않습니다!";
            }
        } catch(Exception e){
            e.printStackTrace();
            return "스크랩 취소 실패!";
        }
    }
}
