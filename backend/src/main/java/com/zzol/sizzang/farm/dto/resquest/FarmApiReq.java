package com.zzol.sizzang.farm.dto.resquest;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmApiReq {
    private String p_cert_key;
    private String p_cert_id;
    private String p_returntype;
    private String p_countycode;
}
