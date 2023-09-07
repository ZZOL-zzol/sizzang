package com.zzol.sizzang.market.dto.response;

public interface MarketSearchRes {

    // 검색결과로 반환할 시장 정보

    int getMk_code();
    String getMk_name();
    String getMk_address();
    String getMk_toilet();
    String getMk_parking();
    double getMk_latitude();
    double getMk_longtitude();

}
