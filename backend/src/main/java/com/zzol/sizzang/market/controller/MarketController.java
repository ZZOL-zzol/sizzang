package com.zzol.sizzang.market.controller;


import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //시장검색
    @GetMapping("/search/{mkName}/{limit}/{offset}")
    @Operation(summary = "시장 검색", description = "시장이름으로 검색")
    public CommonResponse<?> searchMarket(@PathVariable String mkName, @PathVariable int limit, @PathVariable int offset) {
        List<MarketSearchRes> data = marketService.searchMarket(mkName, limit, offset);

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
