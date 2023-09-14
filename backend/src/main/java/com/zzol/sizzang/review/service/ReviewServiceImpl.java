package com.zzol.sizzang.review.service;

import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.repository.MarketRepository;
import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.entity.ReviewEntity;
import com.zzol.sizzang.review.repository.ReviewRepository;
import com.zzol.sizzang.s3.service.FileService;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MarketRepository marketRepository;

    private FileService s3Service;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, FileService s3Service, StoreRepository storeRepository, MarketRepository marketRepository){
        this.reviewRepository = reviewRepository;
        this.s3Service = s3Service;
        this.storeRepository = storeRepository;
        this.marketRepository = marketRepository;
    }

    //리뷰전체 불러오기(점포별)
    @Override
    public List<ReviewEntity> getAllReviews(long stCode){
        return reviewRepository.findByStCode(stCode);
    }


    //리뷰 등록하기
    @Override
    public void addReview(ReviewAddReq reviewAddReq){
        ReviewEntity review = new ReviewEntity();

        review.setUserCode(reviewAddReq.getUserCode());
        review.setStCode(reviewAddReq.getStCode());
        review.setPuCode(reviewAddReq.getPuCode());
        review.setReTitle(reviewAddReq.getReTitle());
        review.setReContent(reviewAddReq.getReContent());
        review.setReImg(reviewAddReq.getReImg());
        review.setReScore(reviewAddReq.getReScore());

        reviewRepository.save(review);

        double stScore = reviewRepository.getReviewScore(reviewAddReq.getStCode());
        StoreEntity storeUpdateTarget = storeRepository.findByStCode(reviewAddReq.getStCode()).get();
        storeUpdateTarget.setStScore(stScore);

        storeRepository.save(storeUpdateTarget);

        int marketCode = storeRepository.findByStCode(reviewAddReq.getStCode()).get().getMarketEntity().getMkCode();
        double mkScore = storeRepository.getReviewScore(marketCode);
        MarketEntity mkScoreUpdateTarget = marketRepository.findByMkCode(marketCode).get();
        mkScoreUpdateTarget.setMkScore(mkScore);

        marketRepository.save(mkScoreUpdateTarget);


    }

    @Override
    public String addReviewImg(MultipartFile file) {
        String imgPath = s3Service.saveFile(file);
        return "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
    }


}
