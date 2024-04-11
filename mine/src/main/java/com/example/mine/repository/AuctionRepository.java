package com.example.mine.repository;

import com.example.mine.entity.AuctionEntity;
import com.example.mine.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
    List<AuctionEntity> findByAuctioncategoryContaining(String auctionCategory);
    List<AuctionEntity> findByAuctionuserContaining(String auctionUser);
    List<AuctionEntity> findByAuctiontitleContaining(String auctionTitle);
    List<AuctionEntity> findByAuctioncontentContaining(String auctionContent);
    @Query("SELECT a FROM AuctionEntity a WHERE a.auctioncontent LIKE %:keyword% OR a.auctiontitle LIKE %:keyword%")
    List<AuctionEntity> findByKeywordInContentOrTitle(@Param("keyword") String keyword);
}
