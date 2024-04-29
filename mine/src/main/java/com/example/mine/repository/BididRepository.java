package com.example.mine.repository;

import com.example.mine.entity.BididEntity;
import com.example.mine.entity.WriteidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BididRepository extends JpaRepository<BididEntity, Long> {
    Optional<BididEntity> findByBidid(String bidid);
}
