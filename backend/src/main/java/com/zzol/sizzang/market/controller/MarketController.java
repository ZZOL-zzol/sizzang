package com.zzol.sizzang.market.controller;

import com.zzol.sizzang.common.exception.Template.TemplateNoResultException;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.market.service.MarketService;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @GetMapping("/search/{marketName}/{limit}/{offset}")
    @Operation(summary = "시장 검색", description = "시장이름으로 검색")
    public CommonResponse<?> searchMarket(@PathVariable String marketName, @PathVariable int limit, @PathVariable int offset) {

        List<MarketSearchRes> data = marketService.searchMarket(marketName, limit, offset);

        return CommonResponse.success(data);
    }


}
