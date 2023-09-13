package com.zzol.sizzang.review.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.dto.request.ReviewGetReq;
import com.zzol.sizzang.review.entity.ReviewEntity;
import com.zzol.sizzang.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
public class ReviewController {



    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //시장검색
    @PostMapping("/getAll")
    @Operation(summary = "점포별 리뷰 불러오기")
    public CommonResponse<?> getAllReviews(@RequestBody ReviewGetReq reviewGetReq) {
        List<ReviewEntity> data = reviewService.getAllReviews(reviewGetReq.getStCode());

        return CommonResponse.success(data);
    }
    @PostMapping("/add")
    @Operation(summary = "리뷰등록")
    public CommonResponse<?> addReview(@RequestBody ReviewAddReq reviewAddReq) {
        reviewService.addReview(reviewAddReq);

        return CommonResponse.success("리뷰가 성공적으로 등록되었습니다.");
    }

    @PostMapping("/add/img")
    public CommonResponse<?> addReviewImg(@RequestPart MultipartFile file){
        String data = reviewService.addReviewImg(file);
        return CommonResponse.success(data);
    }

}
