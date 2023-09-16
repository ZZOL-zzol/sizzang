package com.zzol.sizzang.review.service;

import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.repository.MarketRepository;
import com.zzol.sizzang.product.entity.PurchaseEntity;
import com.zzol.sizzang.product.repository.PurchaseRepository;
import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.dto.response.ReviewRes;
import com.zzol.sizzang.review.entity.ReviewEntity;
import com.zzol.sizzang.review.repository.ReviewRepository;
import com.zzol.sizzang.s3.service.FileService;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StoreRepository;
import com.zzol.sizzang.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MarketRepository marketRepository;

    private final UserRepository userRepository;

    private final PurchaseRepository purchaseRepository;

    private FileService s3Service;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, FileService s3Service, StoreRepository storeRepository, MarketRepository marketRepository, UserRepository userRepository, PurchaseRepository purchaseRepository){
        this.reviewRepository = reviewRepository;
        this.s3Service = s3Service;
        this.storeRepository = storeRepository;
        this.marketRepository = marketRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    //리뷰전체 불러오기(시장별)
    @Override
    public List<ReviewRes> getAllReviewsByMarket(int mkCode){
        List<ReviewRes> result = new ArrayList<>();
        List<StoreEntity> storeList = storeRepository.findByMarketEntity_MkCode(mkCode);

        for(StoreEntity store : storeList) {
            List<ReviewEntity> reviewList = reviewRepository.findByStCode(store.getStCode());
            for(ReviewEntity r : reviewList) {
                ReviewRes reviewRes = ReviewRes.builder()
                        .reCode(r.getReCode())
                        .userCode(r.getUserCode())
                        .stCode(r.getStCode())
                        .puCode(r.getPuCode())
                        .reTitle(r.getReTitle())
                        .reContent(r.getReContent())
                        .reImg(r.getReImg())
                        .reScore(r.getReScore())
                        .createTime(r.getCreateTime().toString())
                        .userNickname(userRepository.findByUserCode(r.getUserCode()).get().getUserNickname())
                        .stName(storeRepository.findByStCode(r.getStCode()).get().getStName())
                        .build();
                result.add(reviewRes);
            }
        }
        Collections.sort(result, (o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return result;
    }


    //리뷰전체 불러오기(점포별)
    @Override
    public List<ReviewRes> getAllReviewsByStore(Long stCode){

        List<ReviewRes> result = reviewRepository.findByStCode(stCode)
                        .stream().map(r ->ReviewRes.builder()
                        .reCode(r.getReCode())
                        .userCode(r.getUserCode())
                        .stCode(r.getStCode())
                        .puCode(r.getPuCode())
                        .reTitle(r.getReTitle())
                        .reContent(r.getReContent())
                        .reImg(r.getReImg())
                        .reScore(r.getReScore())
                        .createTime(r.getCreateTime().toString())
                        .userNickname(userRepository.findByUserCode(r.getUserCode()).get().getUserNickname())
                        .stName(storeRepository.findByStCode(r.getStCode()).get().getStName())
                        .build()).collect(Collectors.toList());

        Collections.sort(result, (o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));

        return result;
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

        PurchaseEntity pu = purchaseRepository.findByPuCode(reviewAddReq.getPuCode()).get();
        pu.setReRegisted(true);
        purchaseRepository.save(pu);

    }

    @Override
    public String addReviewImg(MultipartFile file) {
        String imgPath = s3Service.saveFile(file);
        return "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
    }


}
