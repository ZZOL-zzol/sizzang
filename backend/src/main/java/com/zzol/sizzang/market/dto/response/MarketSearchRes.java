package com.zzol.sizzang.market.dto.response;

public interface MarketSearchRes {

// 검색결과로 반환할 시장 정보
// 시장코드, 시장이름, 시장주소, 화장실여부, 주차장여부 반환예정.
    int getMk_code();
    String getMk_name();
    String getMk_address();
    String getMk_toilet();
    String getMk_parking();


}
