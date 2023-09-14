package com.zzol.sizzang.market.controller;


import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.market.dto.request.MarketGetReq;
import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    //시장 전체 불러오기
    @PostMapping("/getAll")
    @Operation(summary = "시장 전체 불러오기", description = "시장 전체 불러오기")
    public CommonResponse<?> getAllMarket(@RequestBody MarketGetReq marketGetReq) {
        List<MarketEntity> data = marketService.getAllMarket(marketGetReq);

        return CommonResponse.success(data);
    }

    //시장검색
    @PostMapping("/search")
    @Operation(summary = "시장 검색", description = "시장이름으로 검색")
    public CommonResponse<?> searchMarket(@RequestBody MarketGetReq marketGetReq) {
        List<MarketEntity> data = marketService.searchMarket(marketGetReq);

        return CommonResponse.success(data);
    }

    // 시장 상세
    @GetMapping("/info/{mkCode}")
    @Operation(summary = "시장 상세", description = "시장코드로 상세 보기")
    public CommonResponse<?> getMarketDetails(@PathVariable int mkCode) {

        Optional<MarketEntity> data = marketService.getMarketDetails(mkCode);

        return CommonResponse.success(data);
    }



}
