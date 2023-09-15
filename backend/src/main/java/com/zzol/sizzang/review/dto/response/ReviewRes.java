package com.zzol.sizzang.review.dto.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@Builder
public class ReviewRes {

    private Long reCode;
    private Long userCode;
    private Long stCode;
    private Long puCode;
    private String reTitle;
    private String reContent;
    private String reImg;
    private int reScore;
    private String createTime; //원래 entity에는 datetime임.
    private String userNickname;
    private String stName;

}
