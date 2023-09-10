package com.zzol.sizzang.review.service;

import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.review.dto.response.ReviewRes;
import com.zzol.sizzang.review.entity.ReviewEntity;
import com.zzol.sizzang.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    //리뷰전체 불러오기(점포별)
    @Override
    public List<ReviewEntity> getAllReview(int stCode){
        return reviewRepository.findByStCode(stCode);
    }

    //리뷰 등록하기 (유저코드, 점포코드, 제목, 내용, 이미지, 점수)


}
