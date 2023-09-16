package com.zzol.sizzang.product.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.product.dto.request.PurchaseRegistRes;
import com.zzol.sizzang.product.service.PurchaseService;
import com.zzol.sizzang.review.dto.request.ReviewGetReq;
import com.zzol.sizzang.review.dto.response.ReviewRes;
import com.zzol.sizzang.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    //구매이력 등록하기
    @PostMapping("/regist")
    @Operation(summary = "점포별 리뷰 불러오기")
    public CommonResponse<?> registPurchase(@RequestBody PurchaseRegistRes purchaseRegistRes) {
        purchaseService.addPurchase(purchaseRegistRes);
        return CommonResponse.success("OK");
    }

}
