package com.zzol.sizzang.store.dto.request;

import lombok.Data;

@Data
public class StoreRegistInsertReq {

    private int scCode;
    private int mkCode;
    private Long userCode;
    private String stName;
    private String stPhone;
    private String stImg;
    private String stAccount;
    private String stAccountHolder;
    private String stIntro;
    private String stTime;
    private String stLatitude;
    private String stLongtitude;
    private String stAddress;
}
