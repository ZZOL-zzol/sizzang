package com.zzol.sizzang.farm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "marketprice")
public class MarketPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mpCode;
    @Column(name = "condition", nullable = true)
    private String condition;
    private String productClsCode;
    private String productClsName;
    private String categoryName;
    private String lastestDay;
    private String productName;
    private String itemName;
    private String unit;
    private int dpr1;
    private int dpr2;
    private int dpr3;
    private int dpr4;


}
