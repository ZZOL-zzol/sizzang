package com.zzol.sizzang.store.controller;

import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> insertStore(@RequestBody StoreRegistInsertReq insertInfo) {

        log.info("TemplateController_regist_start: " + insertInfo.toString());

        storeService.insertStore(insertInfo);

        log.info("StoreController_insert_end: success");
        return ResponseEntity<>(insertInfo, HttpStatus.OK);
    }
    /**
     * Store List 조회
     *
     * @return
     */
    @Operation(description = "점포 전체 조회 메서드입니다.")
    @GetMapping()
    public ResponseEntity<List<StoreEntity>> getStores() {
        List<StoreEntity> stores = storeService.getStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }
}
