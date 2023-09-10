package com.zzol.sizzang.store.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Collections;

@Data
@Builder
public class StoreFindRes {
    //시장코드 넣어야함
//    Long mkCode;
    Long stCode;
    String stName;
    String stImg;
    private double stLatitude;
    private double stLongtitude;
    //별점, 리뷰
    //카테고
}