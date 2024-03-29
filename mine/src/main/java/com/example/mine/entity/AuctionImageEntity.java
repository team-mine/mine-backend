package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "auctionimage")
@Data
public class AuctionImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionimageid;

    @Column(name = "imagepath")
    private String auctionimagepath;

    @ManyToOne
    @JoinColumn(name = "auctionid")
    private AuctionEntity auctionentity;
}