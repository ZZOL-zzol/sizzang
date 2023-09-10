package com.zzol.sizzang.review.service;

import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    List<ReviewEntity> getAllReviews(long stCode);  //전부 가져오기

    void addReview(ReviewAddReq reviewAddReq);  //리뷰 등록하기
    
}
