package com.zzol.sizzang.review.dto.request;

import lombok.Data;


@Data
public class ReviewAddReq {

    private int userCode;
    private int stCode;
    private String reTitle;
    private String reContent;
    private String reImg;
    private int reScore;

}
