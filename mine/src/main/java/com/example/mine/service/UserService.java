package com.example.mine.service;

import com.example.mine.dto.UserDto;
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

            System.out.println(username);
            System.out.println(user);

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
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return userdtos;
    }
}
