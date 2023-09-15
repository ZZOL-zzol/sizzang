package com.zzol.sizzang.store.controller;

import com.zzol.sizzang.common.exception.Template.*;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.product.dto.response.ProductFindRes;
import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindByUserRes;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.dto.response.StoreSelectRes;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommonResponse<?> insertStore(@RequestPart StoreRegistInsertReq registInfo, @RequestPart(value = "file", required = false) MultipartFile file) {

        if (file != null) { // 게시물에 파일 있으면
            log.info("StoreController_regist_start: " + registInfo.toString() + ", "
                    + file);
        } else {
            log.info("StoreController_regist_start: " + registInfo.toString());
        }

        StoreEntity storeEntity = storeService.registStore(registInfo, file);
        if (storeEntity != null) {  // regist 성공하면 success
            log.info("StoreController_regist_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 실패하면 Exception
            throw new FileIOException();
        }
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
     * 시장별 Store List 조회
     *
     * @return
     */
    @Operation(description = "점포 전체 조회 메서드입니다.")
    @GetMapping("/market/{mkCode}")
    public CommonResponse<List<StoreFindRes>> findAllByMkCode(@PathVariable int mkCode) {
        log.info("TemplateController_findAll_start: ");

        Optional<List<StoreFindRes>> findRes = Optional.ofNullable(
                storeService.selectAllStoreByMarket(mkCode));

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
     * 유저가 게시글의 상세 정보를 확인하기 위한 API
     *
     * @param stCode
     */
    @GetMapping("/{stCode}")
    public CommonResponse<?> find(@PathVariable Long stCode) {

        log.info("StoreController_find_start: " + stCode);

        StoreSelectRes findInfo = storeService.selectStore(stCode);

        if (findInfo != null) { // 조회 성공하면 조회 결과 return
            log.info("ArticleController_find_end: " + findInfo.toString());
            return CommonResponse.success(findInfo);
        } else {    // 조회 실패하면 Exception
            throw new StoreNotFoundException();
        }
    }


    /**
     * 점포 modify API 에 대한 서비스
     *
     * @param modifyInfo : 점포 수정할 때 입력한 정보
     * @param fileList   : 점포 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommonResponse<?> modify(@RequestPart StoreModifyPutReq modifyInfo,
                                    @RequestPart(value = "file", required = false) MultipartFile fileList) {

        if (fileList != null) {
            log.info("StoreController_modify_start: " + modifyInfo.toString() + ", "
                    + fileList);
        } else {
            log.info("StoreController_modify_start: " + modifyInfo.toString());
        }
        boolean isModified = storeService.modifyStore(modifyInfo, fileList);

        if (isModified) {   // 수정 성공하면 success
            log.info("ArticleController_modify_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 수정 실패하면 Exception
            throw new StorePossessionFailException();
        }
    }

    /**
     * 점포 삭제하기 위한 API
     */
    @PutMapping("/delete/{stCode}")
    public CommonResponse<?> delete(@PathVariable Long stCode) {

        log.info("StoreController_delete_start: " + stCode);

        boolean isDeleted = storeService.deleteStore(stCode);

        if (isDeleted) {    // 삭제 성공하면 success
            log.info("StoreController_delete_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 삭제 실패하면 Exception
            throw new TemplatePossessionFailException();
        }
    }

    @GetMapping("/score/{stCode}")
    public CommonResponse<?> getStoreScore(@PathVariable long stCode) {

        double data = storeService.getStoreScore(stCode);

        return CommonResponse.success(data);

        }

    /**
     * User별 점포 List 조회
     *
     * @return
     */
    @Operation(description = "유저별 점포 조회 메서드입니다.")
    @GetMapping("/user/{userCode}")
    public CommonResponse<List<StoreFindByUserRes>> findAllByUser(@PathVariable Long userCode) {
        log.info("StoreController_findAllByUser_start: ");

        Optional<List<StoreFindByUserRes>> findRes = Optional.ofNullable(
                storeService.findByStoreByUser(userCode));

        log.info("StoreController_findAllByUser_end: " + findRes);
        return CommonResponse.success(findRes.orElseThrow(TemplateNoResultException::new));
    }
}
