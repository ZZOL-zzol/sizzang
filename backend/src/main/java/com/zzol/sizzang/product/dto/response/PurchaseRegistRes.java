package com.zzol.sizzang.product.dto.response;

import com.zzol.sizzang.product.entity.PurchaseDetailEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRegistRes {

    private Long puCode;
    private Boolean reRegisted;
    private Long stCode;
    private int puCost;
    private String accountNumber;
    private String puDate;
    private List<PurchaseDetailEntity> details;
}
