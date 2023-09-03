package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StCategoryRepository;
import com.zzol.sizzang.store.repository.StoreRepositoty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepositoty storeRepositoty;
    private final StCategoryRepository stCategoryRepository;
    /**
     * 점포 등록
     */
    public void insertStore(){
        final StoreEntity storeEntity = StoreEntity.builder().build();
//        storeRepositoty.save(storeEntity);
    }
}
