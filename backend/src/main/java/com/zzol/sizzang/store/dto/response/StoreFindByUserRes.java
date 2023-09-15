package com.zzol.sizzang.store.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreFindByUserRes {
    private String stName;
    private String mkName;
    private String mkAddress;
    private String stAddress;
    private String scName;
    private String stPhone;
    private String stTime;
    private String stIntro;
}
