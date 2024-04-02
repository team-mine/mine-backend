package com.example.mine.service;

import com.example.mine.dto.AuctionDto;
import com.example.mine.entity.AuctionEntity;
import com.example.mine.entity.AuctionImageEntity;
import com.example.mine.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
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

            // 이미지 엔티티 저장
            List<AuctionImageEntity> imageEntities = saveImages(auctionDto.getAuctionimage(), auctionEntity);
            auctionEntity.setAuctionimages(imageEntities);

            auctionRepository.save(auctionEntity);
            return "글 작성 완료!";
        } catch (Exception e) {
            e.printStackTrace();
            return "505 예기치 못한 오류입니다";
        }
    }

    public List<AuctionDto> getAuction() {
        List<AuctionDto> auctionDtos = new ArrayList<>();
        List<AuctionEntity> auctionEntities = auctionRepository.findAll();
        for (AuctionEntity entity : auctionEntities) {
            AuctionDto dto = new AuctionDto();
            dto.setAuctionid(entity.getAuctionid());
            dto.setAuctiontitle(entity.getAuctiontitle());
            dto.setAuctioncontent(entity.getAuctioncontent());
            dto.setAuctioncategory(entity.getAuctioncategory());
            dto.setAuctionuser(entity.getAuctionuser());
            dto.setAuctiontime(entity.getAuctiontime());
            dto.setAuctionendtime(entity.getAuctionendtime());
            dto.setAuctionprice(entity.getAuctionprice());

            // 이미지 경로 추가
            List<String> imageUrls = new ArrayList<>();
            for (AuctionImageEntity imageEntity : entity.getAuctionimages()) {
                imageUrls.add(imageEntity.getAuctionimagepath());
            }
            dto.setAuctionimageurl(imageUrls);

            auctionDtos.add(dto);
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

                    // 이미지 파일 이름 설정
                    String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

                    // 이미지 파일 경로 설정
                    Path filePath = Paths.get(uploadPath.getAbsolutePath() + File.separator + fileName);

                    // 이미지 파일 저장
                    Files.write(filePath, image.getBytes());

                    // 이미지 엔티티 생성 및 추가
                    AuctionImageEntity imageEntity = new AuctionImageEntity();
                    imageEntity.setAuctionimagepath(uploadDir + fileName);
                    imageEntity.setAuctionentity(auctionEntity); // AuctionEntity 설정
                    imageEntities.add(imageEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("imageEntities:" + imageEntities);
        return imageEntities;
    }
}
