package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "auction")
@Data
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionid;

    @Column(name = "auctiontitle")
    private String auctiontitle;

    @Column(name = "auctioncontent")
    private String auctioncontent;

    @Column(name = "auctionuser")
    private String auctionuser;

    @Column(name = "auctiontime")
    private String auctiontime;

    @Column(name = "auctioncategory")
    private String auctioncategory;

    @Column(name = "auctionprice")
    private String auctionprice;

    @Column(name = "auctionendtime")
    private String auctionendtime;

    @OneToMany(mappedBy = "auctionentity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionImageEntity> auctionimages;
}
