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
import java.util.Optional;
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
            auctionEntity.setAuctionbidprice(auctionDto.getAuctionbidprice());
            auctionEntity.setAuctiondirectbid(auctionDto.getAuctiondirectbid());
            auctionEntity.setAuctionbidsnum(0L);

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

        try {
            List<AuctionEntity> auctionEntities = auctionRepository.findAll();
            for (AuctionEntity entity : auctionEntities) {
                AuctionDto auctionDto = new AuctionDto();
                auctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                auctionDto.setAuctiontitle(entity.getAuctiontitle());
                auctionDto.setAuctioncontent(entity.getAuctioncontent());
                auctionDto.setAuctioncategory(entity.getAuctioncategory());
                auctionDto.setAuctionuser(entity.getAuctionuser());
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
        return imageEntities;
    }

    public String updateAuction(AuctionDto auctionDto){
        try{
            Long auctionId = auctionDto.getAuctionid();
            List<MultipartFile> newImages = auctionDto.getAuctionimage();

            Optional<AuctionEntity> auctionOptional = auctionRepository.findById(auctionId);

            if (auctionOptional.isPresent()) {
                AuctionEntity auctionEntity = auctionOptional.get();

                auctionEntity.getAuctionimages().forEach(image -> image.setAuctionentity(null));
                auctionEntity.getAuctionimages().clear();

                List<AuctionImageEntity> newImageEntities = saveImages(newImages, auctionEntity);
                auctionEntity.getAuctionimages().addAll(newImageEntities);

                auctionEntity.setAuctioncategory(auctionDto.getAuctioncategory());
                auctionEntity.setAuctiontitle(auctionDto.getAuctiontitle());
                auctionDto.setAuctioncontent(auctionDto.getAuctioncontent());

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

            System.out.println("auctionid:" + auctionId);

            Optional<AuctionEntity> auctionOptional = auctionRepository.findById(auctionId);

            if (auctionOptional.isPresent()) {
                AuctionEntity auctionEntity = auctionOptional.get();

                auctionEntity.setAuctionbidprice(auctionbidPrice);

                auctionEntity.setAuctionbidder(auctionbidder);

                auctionEntity.setAuctionbidsnum(auctionbidsnum);

                auctionRepository.save(auctionEntity);

                return "경매 입찰가 업데이트 완료";
            } else {
                return "해당하는 경매가 존재하지 않습니다";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "505 예기치 못한 오류입니다";
        }
    }

    public void auctiondelete(Long auctionid){auctionRepository.deleteById(auctionid);}
}
