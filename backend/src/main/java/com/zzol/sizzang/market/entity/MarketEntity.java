package com.zzol.sizzang.market.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
@Table(name = "market")
public class MarketEntity {

    @Id
    @Column(name = "mk_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mkCode;
    @Column(name = "region_code")
    private int regionCode;
    @Column(name = "mk_name")
    private String mkName;
    @Column(name = "mk_address")
    private String mkAddress;
    @Column(name = "mk_img")
    private String mkImg;
    @Column(name = "mk_toilet")
    private String mkToilet;
    @Column(name = "mk_parking")
    private String mkParking;
    @Column(name = "mk_phone")
    private String mkPhone;
    @Column(name = "mk_info")
    private String mkInfo;
    @Column(name = "mk_latitude")
    private double mkLatitude;
    @Column(name = "mk_longtitude")
    private double mkLongtitude;

}
