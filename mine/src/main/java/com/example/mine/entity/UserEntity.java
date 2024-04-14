package com.example.mine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usertable")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "user")
    private String user;

    @Column(name = "username")
    private String username;

    @Column(name = "bidid")
    private Long bidid;

    @Column(name = "writeid")
    private Long writeid;
}
