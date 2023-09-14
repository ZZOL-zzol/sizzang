package com.zzol.sizzang.product.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrTagFindRes {
    String tagName;
    int tagCode;
    String tagUnit;
    int tagCost;
}
