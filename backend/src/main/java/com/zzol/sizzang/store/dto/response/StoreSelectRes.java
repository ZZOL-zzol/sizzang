package com.zzol.sizzang.store.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreSelectRes {
//    시장코드 넣어야함
    private int mkCode;
    private String stOwner;
    private String scName;
    private Long stCode;
    private String stName;
    private String stPhone;
    private String stImg;
    private String stAccount;
    private String stAccountHolder;
    private String stIntro;
    private String stTime;
    private String stLatitude;
    private String stLongtitude;
    private double stScore;
    private String stAddress;
}