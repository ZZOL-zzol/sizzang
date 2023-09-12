package com.zzol.sizzang.product.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFindRes {

    Long pdCode;
    int tagCode;
    Long stCode;
    String pdName;
    int pdCost;
    String pdIntro;
    int tagCost;
//    String pdImg;
}
