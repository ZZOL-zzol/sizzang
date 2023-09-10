package com.zzol.sizzang.review.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.entity.ReviewEntity;
import com.zzol.sizzang.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getAll/{stCode}")
    @Operation(summary = "점포별 리뷰 불러오기")
    public CommonResponse<?> searchMarket(@PathVariable int stCode) {
        List<ReviewEntity> data = reviewService.getAllReview(stCode);

        return CommonResponse.success(data);
    }
    @PostMapping("/add")
    @Operation(summary = "리뷰등록")
    public CommonResponse<?> searchMarket(@RequestBody ReviewAddReq reviewAddReq) {
        List<ReviewEntity> data = reviewService.addReview(reviewAddReq);

        return CommonResponse.success(data);
    }




}
