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
    private String conditionStr;
    private String productClsCode;
    private String productClsName;
    private String categoryName;
    private String lastestDay;
    private String productName;
    private String itemName;
    private String unit;
    private int direction;
    private double value;
    private int dpr1;
    private int dpr2;
    private int dpr3;
    private int dpr4;

}
