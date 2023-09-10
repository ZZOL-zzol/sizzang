package com.zzol.sizzang.review.service;

import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {

    List<ReviewEntity> getAllReview(int stCode);
    List<ReviewEntity> addReview(ReviewAddReq reviewAddReq);
}
