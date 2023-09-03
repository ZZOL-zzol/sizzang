package com.zzol.sizzang.crawling.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MarketPrice {
    private String mpCategory;
    private String mpName;
    private String mpKind;
    private String mpDate;
    private String reCode;
    private int mpCost;
}
