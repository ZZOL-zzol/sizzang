package com.zzol.sizzang.review.dto.request;

import lombok.Data;


@Data
public class ReviewAddReq {

    private Long userCode;
    private Long stCode;
    private Long puCode;
    private String reTitle;
    private String reContent;
    private String reImg;
    private int reScore;

}
