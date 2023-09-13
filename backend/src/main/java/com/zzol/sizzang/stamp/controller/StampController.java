package com.zzol.sizzang.stamp.controller;

import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.stamp.dto.request.StampReq;
import com.zzol.sizzang.stamp.entity.StampEntity;
import com.zzol.sizzang.stamp.service.StampService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stamp")
public class StampController {

    private final StampService stampService;

    @Autowired
    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @PostMapping("/add")
    @Operation(summary = "스탬프추가", description = "그 지역 시장에서 구매한 횟수 기반")
    public CommonResponse<?> addStamp(@RequestBody StampReq stampReq) {

        String data = stampService.addStamp(stampReq);

        return CommonResponse.success(data);
    }

    @PostMapping("/getAll")
    @Operation(summary = "스탬프 불러오기", description = "회원번호로 스탬프 전부 불러오기")
    public CommonResponse<List<StampEntity>> getStampList (@RequestBody StampReq stampReq) {
        List<StampEntity> data = stampService.getStampList(stampReq);
        return CommonResponse.success(data);
    }
}
