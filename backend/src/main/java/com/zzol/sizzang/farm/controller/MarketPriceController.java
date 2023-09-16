package com.zzol.sizzang.farm.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.farm.dto.response.FarmAllRes;

import com.zzol.sizzang.farm.dto.response.FarmRegionalRes;
import com.zzol.sizzang.farm.dto.resquest.FarmApiReq;
import com.zzol.sizzang.farm.entity.MarketPriceEntity;
import com.zzol.sizzang.farm.service.FarmApiService;
import com.zzol.sizzang.farm.service.MarketPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/marketprice")
public class MarketPriceController {
    private final MarketPriceService marketPriceService;
    private final FarmApiService farmApiService;

    @Autowired
    public MarketPriceController(MarketPriceService marketPriceService, FarmApiService farmApiService) {
        this.marketPriceService = marketPriceService;
        this.farmApiService = farmApiService;
    }


    @GetMapping("/find/items")
    public CommonResponse<?> findCheaperItem(){
        List<MarketPriceEntity> items = marketPriceService.findCheaperItem(0);
        return CommonResponse.success(items);
    }

    @GetMapping("/farm/getAll")
    public CommonResponse<?> getRecentlyPrice(FarmApiReq farmApiReq){
        FarmAllRes data = farmApiService.getRecentlyPrice(farmApiReq);
        return CommonResponse.success(data);
    }

    @GetMapping("/farm/regional")
    public CommonResponse<?> getRegionalPrice(FarmApiReq farmApiReq){
        FarmRegionalRes data = farmApiService.getRegionalPrice(farmApiReq);
        return CommonResponse.success(data);
    }

}
