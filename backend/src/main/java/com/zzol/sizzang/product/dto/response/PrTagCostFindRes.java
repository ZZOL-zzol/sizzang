package com.zzol.sizzang.product.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrTagCostFindRes {

    int tagCode;
    int pdCost;

}
