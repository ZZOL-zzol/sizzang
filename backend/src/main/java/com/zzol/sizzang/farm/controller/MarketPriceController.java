package com.zzol.sizzang.farm.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.farm.dto.response.PriceRes;
import com.zzol.sizzang.farm.service.MarketPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/marketprice")
public class MarketPriceController {
    private final MarketPriceService marketPriceService;

    @Autowired
    public MarketPriceController(MarketPriceService marketPriceService) {
        this.marketPriceService = marketPriceService;
    }


    @GetMapping("/get/wholesale")
    public CommonResponse<?> marketPrice(){
        marketPriceService.getWholesalePrice();
//        List<MarketPrice> marketPriceList = marketPriceService.getMarketPriceData();
//        marketPriceService.getMarketPriceData();
        return CommonResponse.success("OK");

    }
}
