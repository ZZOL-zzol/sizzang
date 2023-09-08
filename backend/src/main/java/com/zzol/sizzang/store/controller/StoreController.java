package com.zzol.sizzang.store.controller;

import com.zzol.sizzang.common.exception.Template.TemplateNoResultException;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
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

    /**
     *  게시글 검색어로 검색하여 조회 API
     */
    @GetMapping("/search")
    public CommonResponse<?> findByCondition(
            FindByConditionGetReq findByConditionGetReq) {

        log.info("StoreController_findByCondition_start: " + findByConditionGetReq.toString());

        // 검색 결과 없을 경우 어떻게 할지 보완 필요 (현재 null 이 아니라 빈 리스트라서 exception 발동 안 함)ㄴ
        Optional<List<StoreFindRes>> findRes = Optional.ofNullable(
                storeService.findByCondition(findByConditionGetReq));

        log.info("StoreController_findByCondition_end: " + findRes);
        return CommonResponse.success(findRes.orElseThrow(TemplateNoResultException::new));
    }


    /**
     * 점포 modify API 에 대한 서비스
     *
     * @param modifyInfo : 게시글 수정할 때 입력한 정보
     * @param fileList   : 게시글 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */


}
