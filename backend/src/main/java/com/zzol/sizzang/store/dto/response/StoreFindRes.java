package com.zzol.sizzang.store.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreFindRes {
    //시장코드 넣어야함
//    Long mkCode;
    private Long stCode;
    private String stName;
    private String stImg;
    private String stLatitude;
    private String stLongtitude;
    private String stIntro;
    //별점, 리뷰
    //카테고

    public StoreFindRes() {
    }

    public StoreFindRes(Long stCode, String stName, String stImg, String stIntro, String stLatitude, String stLongtitude) {
        this.stCode = stCode;
        this.stName = stName;
        this.stImg = stImg;
        this.stIntro = stIntro;
        this.stLatitude = stLatitude;
        this.stLongtitude = stLongtitude;
    }
}