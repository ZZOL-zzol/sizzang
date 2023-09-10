package com.zzol.sizzang.store.dto.request;

import lombok.Data;

@Data
public class StoreModifyPutReq {

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