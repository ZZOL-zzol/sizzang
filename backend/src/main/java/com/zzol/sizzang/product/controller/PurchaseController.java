package com.zzol.sizzang.product.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.product.dto.request.PurchaseRegistReq;
import com.zzol.sizzang.product.dto.response.PurchaseRegistRes;
import com.zzol.sizzang.product.service.PurchaseService;
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
    @Operation(summary = "구매내역 등록하기")
    public CommonResponse<?> registPurchase(@RequestBody PurchaseRegistReq purchaseRegistReq) {
        purchaseService.addPurchase(purchaseRegistReq);
        return CommonResponse.success("OK");
    }

    @PostMapping("/get")
    @Operation(summary = "구매내역 불러오기")
    public CommonResponse<?> getDetailList(@RequestBody PurchaseRegistReq purchaseRegistReq) {
        List<PurchaseRegistRes> data = purchaseService.getDetailList(purchaseRegistReq.getAccountNumber());
        return CommonResponse.success(data);
    }

}
