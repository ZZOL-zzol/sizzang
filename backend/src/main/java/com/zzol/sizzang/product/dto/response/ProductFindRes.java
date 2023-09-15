package com.zzol.sizzang.product.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFindRes {

    Long pdCode;
    String tagName;
    String stName;
    String pdName;
    int pdCost;
    String pdIntro;
    int tagCost;
}
