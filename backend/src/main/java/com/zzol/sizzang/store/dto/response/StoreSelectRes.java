package com.zzol.sizzang.store.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreSelectRes {
//    시장코드 넣어야함
//    Long mkCode;
    private int scCode;

    private Long stCode;
    private String stName;
    private String stPhone;
    private String stImg;
    private String stAccount;
    private String stAccountHolder;
    private String stIntro;
    private String stTime;
    private double stLatitude;
    private double stLongtitude;
}