package com.zzol.sizzang.product.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRegistRes {

//    private Long puCode;
//    private Boolean reRegisted;

    private Long stCode;
    private int puCost;
    private String accountNumber;

    private List<PurchaseDetailDto> details;
}
