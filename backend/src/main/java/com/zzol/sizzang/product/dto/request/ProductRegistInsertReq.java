package com.zzol.sizzang.product.dto.request;

import lombok.Data;

@Data
public class ProductRegistInsertReq {

    private int pcCode;
    private Long stCode;

    private int pdCost;
    private String pdName;
    private String pdIntro;
    private String pdImg;

}
