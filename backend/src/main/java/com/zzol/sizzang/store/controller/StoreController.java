package com.zzol.sizzang.store.controller;

import com.zzol.sizzang.common.exception.Template.TemplateNoResultException;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {

    // log 형식은
    private static final String SUCCESS = "success"; // API 성공 시 return
    private final StoreService storeService;
    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Store 등록
     */
    @Operation(description = "점포 등록 메서드입니다")
    @PostMapping
    public CommonResponse<?> insertStore(@RequestBody StoreRegistInsertReq insertInfo) {

        log.info("TemplateController_regist_start: " + insertInfo.toString());

        storeService.insertStore(insertInfo);

        log.info("StoreController_insert_end: success");
        return CommonResponse.success(SUCCESS);
    }
    /**
     * Store List 조회
     *
     * @return
     */
    @Operation(description = "점포 전체 조회 메서드입니다.")
    @GetMapping()
    public CommonResponse<List<StoreFindRes>> findAll() {
        log.info("TemplateController_findAll_start: ");

        Optional<List<StoreFindRes>> findRes = Optional.ofNullable(
                storeService.selectAllStore());

        log.info("TemplateController_findAll_end: " + findRes);
        return CommonResponse.success(findRes.orElseThrow(TemplateNoResultException::new));
    }



}
