package com.example.mine.repository;

import com.example.mine.entity.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
}
