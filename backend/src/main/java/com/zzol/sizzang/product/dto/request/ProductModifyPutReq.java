package com.zzol.sizzang.product.dto.request;

import lombok.Data;

@Data
public class ProductModifyPutReq {

    private int tagCode;
    private Long stCode;

    private Long pdCode;
    private int pdCost;
    private String pdName;
    private String pdIntro;

}
