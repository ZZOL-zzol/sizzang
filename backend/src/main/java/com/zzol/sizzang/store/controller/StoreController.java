package com.zzol.sizzang.store.controller;

import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.service.StoreService;
import com.zzol.sizzang.user.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class StoreController {
    private StoreService storeService;

    /**
     * Store 등록
     */
    @Operation(description = "점포 등록 메서드입니다.")
    @GetMapping()
    public ResponseEntity<?> insertStore(StoreEntity storeEntity){
        storeService.insertStore();
        return new ResponseEntity<StoreEntity>(storeEntity, HttpStatus.OK);
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
