package com.zzol.sizzang.stamp.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.stamp.dto.request.StampAddReq;
import com.zzol.sizzang.stamp.service.StampService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StampController {

    private final StampService stampService;

    @Autowired
    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @PostMapping("/addStamp")
    @Operation(summary = "스탬프추가", description = "그 지역 시장에서 구매한 횟수 기반")
    public CommonResponse<?> addStamp(@RequestBody StampAddReq stampAddReq) {

        stampService.addStamp(stampAddReq);

        return CommonResponse.success("OK");
    }
}
