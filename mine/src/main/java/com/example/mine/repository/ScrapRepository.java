package com.example.mine.repository;

import com.example.mine.entity.ScrapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrapRepository extends JpaRepository<ScrapEntity, Long> {
    Optional<ScrapEntity> findByScrapid(String scrapid);
}
