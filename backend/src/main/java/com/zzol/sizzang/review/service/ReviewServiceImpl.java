package com.zzol.sizzang.review.service;

import com.zzol.sizzang.review.dto.request.ReviewAddReq;
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
    public List<ReviewEntity> getAllReviews(long stCode){
        return reviewRepository.findByStCode(stCode);
    }

    //리뷰 상세보기
    @Override
    public ReviewEntity getReview(long reCode){
        return reviewRepository.findByReCode(reCode);
    }

    //리뷰 등록하기
    @Override
    public List<ReviewEntity> addReview(ReviewAddReq reviewAddReq){
        ReviewEntity review = new ReviewEntity();

        review.setUserCode(reviewAddReq.getUserCode());
        review.setStCode(reviewAddReq.getStCode());
        review.setPuCode(reviewAddReq.getPuCode());
        review.setReTitle(reviewAddReq.getReTitle());
        review.setReContent(reviewAddReq.getReContent());
        review.setReImg(reviewAddReq.getReImg());
        review.setReScore(reviewAddReq.getReScore());

        reviewRepository.save(review);
        return reviewRepository.findByStCode(reviewAddReq.getStCode());
    }




}
