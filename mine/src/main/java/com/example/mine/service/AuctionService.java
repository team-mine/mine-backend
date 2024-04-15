package com.example.mine.service;

import com.example.mine.dto.AuctionDto;
import com.example.mine.entity.AuctionEntity;
import com.example.mine.entity.AuctionImageEntity;
import com.example.mine.entity.UserEntity;
import com.example.mine.repository.AuctionRepository;
import com.example.mine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository, UserRepository userRepository) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    public String saveAuction(AuctionDto auctionDto) {
        try {
            AuctionEntity auctionEntity = new AuctionEntity();
            auctionEntity.setAuctiontitle(auctionDto.getAuctiontitle());
            auctionEntity.setAuctioncontent(auctionDto.getAuctioncontent());
            auctionEntity.setAuctioncategory(auctionDto.getAuctioncategory());
            auctionEntity.setAuctiontime(LocalDateTime.now().toString());
            auctionEntity.setAuctionendtime(auctionDto.getAuctionendtime());
            auctionEntity.setAuctionprice(auctionDto.getAuctionprice());
            auctionEntity.setAuctionuser(auctionDto.getAuctionuser());
            auctionEntity.setAuctionusername(auctionDto.getAuctionusername());
            auctionEntity.setAuctionbidprice(auctionDto.getAuctionbidprice());
            auctionEntity.setAuctiondirectbid(auctionDto.getAuctiondirectbid());
            auctionEntity.setAuctionbidsnum(0L);

            List<AuctionImageEntity> imageEntities = saveImages(auctionDto.getAuctionimage(), auctionEntity);
            auctionEntity.setAuctionimages(imageEntities);

            auctionRepository.save(auctionEntity);

            Optional<UserEntity> userOptional = userRepository.findByUser(auctionDto.getAuctionuser());
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();

                userEntity.setWriteid(auctionDto.getAuctionid());

                userRepository.save(userEntity);
            }else{
                return "유저가 존재하지 않습니다.";
            }

            return "글 작성 완료!";
        } catch (Exception e) {
            e.printStackTrace();
            return "505 예기치 못한 오류입니다";
        }
    }

    public List<AuctionDto> getAuction() {
        List<AuctionDto> auctionDtos = new ArrayList<>();

        try {
            List<AuctionEntity> auctionEntities = auctionRepository.findAll();
            for (AuctionEntity entity : auctionEntities) {
                AuctionDto auctionDto = new AuctionDto();
                auctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                auctionDto.setAuctiontitle(entity.getAuctiontitle());
                auctionDto.setAuctioncontent(entity.getAuctioncontent());
                auctionDto.setAuctioncategory(entity.getAuctioncategory());
                auctionDto.setAuctionuser(entity.getAuctionuser());
                auctionDto.setAuctionusername(entity.getAuctionusername());
                auctionDto.setAuctiontime(entity.getAuctiontime());
                auctionDto.setAuctionendtime(entity.getAuctionendtime());
                auctionDto.setAuctionprice(entity.getAuctionprice());
                auctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                auctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                auctionDto.setAuctionbidder(entity.getAuctionbidder());
                auctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());

                // 이미지 경로 추가
                List<String> imageUrls = new ArrayList<>();
                for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                    String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                    imageUrls.add(imageUrl);
                    if (i == 0) {
                        auctionDto.setAuctionfirsturl(imageUrl);
                    }
                }
                auctionDto.setAuctionimageurl(imageUrls);

                auctionDtos.add(auctionDto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return auctionDtos;
    }

    public AuctionDto getBoardAuction(AuctionDto auctionDto){
        AuctionDto auctionDtos = new AuctionDto();

        try {
            Long auctionId = auctionDto.getAuctionid();
            Optional<AuctionEntity> auctionOptional = auctionRepository.findById(auctionId);
            if (auctionOptional.isPresent()) {
                AuctionEntity entity = auctionOptional.get();

                auctionDtos.setAuctionid(Long.valueOf(entity.getAuctionid()));
                auctionDtos.setAuctiontitle(entity.getAuctiontitle());
                auctionDtos.setAuctioncontent(entity.getAuctioncontent());
                auctionDtos.setAuctioncategory(entity.getAuctioncategory());
                auctionDtos.setAuctionuser(entity.getAuctionuser());
                auctionDtos.setAuctionusername(entity.getAuctionusername());
                auctionDtos.setAuctiontime(entity.getAuctiontime());
                auctionDtos.setAuctionendtime(entity.getAuctionendtime());
                auctionDtos.setAuctionprice(entity.getAuctionprice());
                auctionDtos.setAuctionbidprice(entity.getAuctionbidprice());
                auctionDtos.setAuctiondirectbid(entity.getAuctiondirectbid());
                auctionDtos.setAuctionbidder(entity.getAuctionbidder());
                auctionDtos.setAuctionbidsnum(entity.getAuctionbidsnum());

                List<String> imageUrls = new ArrayList<>();
                for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                    String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                    imageUrls.add(imageUrl);
                    if (i == 0) {
                        auctionDtos.setAuctionfirsturl(imageUrl);
                    }
                }
                auctionDtos.setAuctionimageurl(imageUrls);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return auctionDtos;
    }

    private List<AuctionImageEntity> saveImages(List<MultipartFile> images, AuctionEntity auctionEntity) {
        List<AuctionImageEntity> imageEntities = new ArrayList<>();
        try {
            if (images != null) { // 이미지가 첨부되었는지 확인
                for (MultipartFile image : images) {
                    // 이미지 저장 폴더 경로 설정
                    String uploadDir = "uploads/";
                    File uploadPath = new File(uploadDir);
                    if (!uploadPath.exists()) {
                        uploadPath.mkdirs();
                    }

                    String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

                    Path filePath = Paths.get(uploadPath.getAbsolutePath() + File.separator + fileName);

                    Files.write(filePath, image.getBytes());

                    AuctionImageEntity imageEntity = new AuctionImageEntity();
                    imageEntity.setAuctionimagepath(uploadDir + fileName);
                    imageEntity.setAuctionentity(auctionEntity); // AuctionEntity 설정
                    imageEntities.add(imageEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageEntities;
    }

    private List<AuctionImageEntity> saveoldImages(List<String> oldImageNames, AuctionEntity auctionEntity) {
        try {
            return oldImageNames.stream()
                    .map(imageName -> {
                        AuctionImageEntity auctionImageEntity = new AuctionImageEntity();
                        auctionImageEntity.setAuctionimagepath(imageName);
                        auctionImageEntity.setAuctionentity(auctionEntity);
                        return auctionImageEntity;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // 예외 발생 시 로깅
            System.err.println("Error while creating AuctionImageEntity: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public String updateAuction(AuctionDto auctionDto){
        try{
            Long auctionId = auctionDto.getAuctionid();
            List<MultipartFile> newImages = auctionDto.getAuctionimage();
            List<String> oldImages = auctionDto.getAuctionoldimg();

            Optional<AuctionEntity> auctionOptional = auctionRepository.findById(auctionId);

            if (auctionOptional.isPresent()) {
                AuctionEntity auctionEntity = auctionOptional.get();

                if (!auctionEntity.getAuctionuser().equals(auctionDto.getAuctionuser())) {
                    return "해당 경매글의 작성자가 아닙니다.";
                }

                auctionEntity.getAuctionimages().forEach(image -> image.setAuctionentity(null));
                auctionEntity.getAuctionimages().clear();

                if (oldImages != null && !oldImages.isEmpty()) {
                    List<AuctionImageEntity> oldImageEntities = saveoldImages(oldImages, auctionEntity);
                    auctionEntity.getAuctionimages().addAll(oldImageEntities);
                }

                List<AuctionImageEntity> newImageEntities = saveImages(newImages, auctionEntity);
                auctionEntity.getAuctionimages().addAll(newImageEntities);

                auctionEntity.setAuctioncategory(auctionDto.getAuctioncategory());
                auctionEntity.setAuctiontitle(auctionDto.getAuctiontitle());
                auctionEntity.setAuctioncontent(auctionDto.getAuctioncontent());

                auctionRepository.save(auctionEntity);

                return "게시글 수정완료";
            }else{
                return "해당하는 경매가 존재하지 않습니다.";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "경매글 수정 실패";
        }
    }

    public String updateAuctionBidPrice(AuctionDto auctionDto) {
        try {
            Long auctionId = auctionDto.getAuctionid();
            String auctionbidPrice = auctionDto.getAuctionbidprice();
            String auctionbidder = auctionDto.getAuctionbidder();
            Long auctionbidsnum = auctionDto.getAuctionbidsnum();
            auctionbidsnum++;

            Optional<AuctionEntity> auctionOptional = auctionRepository.findById(auctionId);

            if (auctionOptional.isPresent()) {
                AuctionEntity auctionEntity = auctionOptional.get();

                auctionEntity.setAuctionbidprice(auctionbidPrice);

                auctionEntity.setAuctionbidder(auctionbidder);

                auctionEntity.setAuctionbidsnum(auctionbidsnum);

                auctionRepository.save(auctionEntity);
            } else {
                return "해당하는 경매가 존재하지 않습니다";
            }

            Optional<UserEntity> userOptional = userRepository.findByUser(auctionbidder);
            if (userOptional.isPresent()) {
                UserEntity userEntity = userOptional.get();

                userEntity.setBidid(auctionId);

                userRepository.save(userEntity);
                
                return "입찰 완료!";
            }else{
                return "유저가 존재하지 않습니다.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "505 예기치 못한 오류입니다";
        }
    }

    public String auctiondelete(AuctionDto auctionDto){
        try {
            Optional<AuctionEntity> auctionOptional = auctionRepository.findById(auctionDto.getAuctionid());

            if (auctionOptional.isPresent()) {
                AuctionEntity auctionEntity = auctionOptional.get();

                if (!auctionEntity.getAuctionuser().equals(auctionDto.getAuctionuser())) {
                    return "해당 경매글의 작성자가 아닙니다. 삭제할 수 없습니다.";
                }

                if(auctionEntity.getAuctionbidsnum() >= 1){
                    return "입찰자가 존재하기 때문에 삭제할 수 없습니다.";
                }

                List<AuctionImageEntity> imagesToDelete = auctionEntity.getAuctionimages();
                for (AuctionImageEntity imageEntity : imagesToDelete) {
                    File file = new File(imageEntity.getAuctionimagepath());
                    if (file.exists()) {
                        file.delete();
                    }
                }

                auctionRepository.deleteById(auctionDto.getAuctionid());
                return "게시글 삭제완료";
            } else {
                return "해당하는 경매가 존재하지 않습니다.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "경매글 삭제 실패";
        }
    }
}
