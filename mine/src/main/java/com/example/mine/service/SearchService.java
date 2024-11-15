package com.example.mine.service;

import com.example.mine.dto.AuctionDto;
import com.example.mine.entity.AuctionEntity;
import com.example.mine.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private final AuctionRepository auctionRepository;

    @Autowired
    public SearchService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<AuctionDto> Categoryget(AuctionDto auctionDto){
        List<AuctionDto> auctionDtos = new ArrayList<>();

        try{
            String auctioncategory = auctionDto.getAuctioncategory();
            List<AuctionEntity> auctionEntities = auctionRepository.findByAuctioncategoryContaining(auctioncategory);

            for (AuctionEntity entity : auctionEntities) {
                if (auctionDto.getAuctiontitle() != null && auctionDto.getAuctiontitle().equals(entity.getAuctiontitle())) {
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    System.out.println("auctiontitle");

                    auctionDtos.add(nauctionDto);
                }else if(auctionDto.getAuctioncontent() != null && auctionDto.getAuctioncontent().equals(entity.getAuctioncontent())){
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else if(auctionDto.getAuctionuser() != null && auctionDto.getAuctionuser().equals(entity.getAuctionuser())){
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else if(auctionDto.getAuctionkeyword() != null && auctionDto.getAuctionkeyword().equals(entity.getAuctiontitle()) && auctionDto.getAuctionkeyword().equals(entity.getAuctioncontent())){
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else{
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);

                    System.out.println("entity: "+ entity.getAuctiontitle());
                    System.out.println("Dto : " + auctionDto.getAuctiontitle());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return auctionDtos;
    }

    public List<AuctionDto> usernameget(AuctionDto auctionDto){
        List<AuctionDto> auctionDtos = new ArrayList<>();

        try{
            String auctionuser = auctionDto.getAuctionuser();
            List<AuctionEntity> auctionEntities = auctionRepository.findByAuctionuserContaining(auctionuser);

            for (AuctionEntity entity : auctionEntities) {
                if(auctionDto.getAuctioncategory() != null && auctionDto.getAuctioncategory().equals(entity.getAuctioncategory())) {
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else{
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return auctionDtos;
    }

    public List<AuctionDto> titleget(AuctionDto auctionDto){
        List<AuctionDto> auctionDtos = new ArrayList<>();

        try{
            String auctiontitle = auctionDto.getAuctiontitle();
            List<AuctionEntity> auctionEntities = auctionRepository.findByAuctiontitleContaining(auctiontitle);

            for (AuctionEntity entity : auctionEntities) {
                if(auctionDto.getAuctioncategory() != null && auctionDto.getAuctioncategory().equals(entity.getAuctioncategory())) {
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else{
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return auctionDtos;
    }

    public List<AuctionDto> contentget(AuctionDto auctionDto){
        List<AuctionDto> auctionDtos = new ArrayList<>();

        try{
            String auctioncontent = auctionDto.getAuctioncontent();
            List<AuctionEntity> auctionEntities = auctionRepository.findByAuctioncontentContaining(auctioncontent);

            for (AuctionEntity entity : auctionEntities) {
                if(auctionDto.getAuctioncategory() != null && auctionDto.getAuctioncategory().equals(entity.getAuctioncategory())) {
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else{
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return auctionDtos;
    }

    public List<AuctionDto> content_and_titleget(AuctionDto auctionDto){
        List<AuctionDto> auctionDtos = new ArrayList<>();

        try{
            String auctionkeyword = auctionDto.getAuctionkeyword();
            List<AuctionEntity> auctionEntities = auctionRepository.findByKeywordInContentOrTitle(auctionkeyword);

            for (AuctionEntity entity : auctionEntities) {
                if(auctionDto.getAuctioncategory() != null && auctionDto.getAuctioncategory().equals(entity.getAuctioncategory())) {
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }else{
                    AuctionDto nauctionDto = new AuctionDto();
                    nauctionDto.setAuctionid(Long.valueOf(entity.getAuctionid()));
                    nauctionDto.setAuctiontitle(entity.getAuctiontitle());
                    nauctionDto.setAuctioncontent(entity.getAuctioncontent());
                    nauctionDto.setAuctioncategory(entity.getAuctioncategory());
                    nauctionDto.setAuctionuser(entity.getAuctionuser());
                    nauctionDto.setAuctionusername(entity.getAuctionusername());
                    nauctionDto.setAuctiontime(entity.getAuctiontime());
                    nauctionDto.setAuctionendtime(entity.getAuctionendtime());
                    nauctionDto.setAuctionprice(entity.getAuctionprice());
                    nauctionDto.setAuctionbidprice(entity.getAuctionbidprice());
                    nauctionDto.setAuctiondirectbid(entity.getAuctiondirectbid());
                    nauctionDto.setAuctionbidder(entity.getAuctionbidder());
                    nauctionDto.setAuctionbidsnum(entity.getAuctionbidsnum());
                    nauctionDto.setAuctioncomplete(entity.isAuctioncomplete());
                    nauctionDto.setAuctionpay(entity.isAuctionpay());

                    // 이미지 경로 추가
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < entity.getAuctionimages().size(); i++) {
                        String imageUrl = entity.getAuctionimages().get(i).getAuctionimagepath();
                        imageUrls.add(imageUrl);
                        if (i == 0) {
                            nauctionDto.setAuctionfirsturl(imageUrl);
                        }
                    }
                    nauctionDto.setAuctionimageurl(imageUrls);

                    auctionDtos.add(nauctionDto);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return auctionDtos;
    }
}
