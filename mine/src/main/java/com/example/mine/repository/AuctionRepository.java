package com.example.mine.repository;

import com.example.mine.entity.AuctionEntity;
import com.example.mine.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
    AuctionEntity findByauctionid(Long auctionId);
}
