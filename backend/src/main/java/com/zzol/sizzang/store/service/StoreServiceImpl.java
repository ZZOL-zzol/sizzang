package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StoreRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepositoty storeRepositoty;

    public StoreServiceImpl(StoreRepositoty storeRepositoty) {
        this.storeRepositoty = storeRepositoty;
    }

    @Override
    public StoreEntity insertStore(StoreRegistInsertReq insertInfo) {
        log.info("TemplateService_registTemplate_start: " + insertInfo.toString());

        int scCode = insertInfo.getScCode();
        String stName = insertInfo.getStName();
        String stPhone = insertInfo.getStPhone();
        String stImg = insertInfo.getStImg();
        String stAccount = insertInfo.getStAccount();
        String stAccountHolder = insertInfo.getStAccountHolder();
        String stIntro = insertInfo.getStIntro();
        String stTime = insertInfo.getStTime();


        StoreEntity storeEntity = StoreEntity.builder()
                .stName(stName)
                .stPhone(stPhone)
                .stImg(stImg)
                .stAccount(stAccount)
                .stAccountHolder(stAccountHolder)
                .stIntro(stIntro)
                .stTime(stTime)
                .build();

        storeRepositoty.save(storeEntity);

        log.info("StoreService_insertStore_end: success");
        return storeEntity;
    }
}
