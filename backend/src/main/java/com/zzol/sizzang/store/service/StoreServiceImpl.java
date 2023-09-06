package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StoreRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepositoty storeRepositoty;

    public StoreServiceImpl(StoreRepositoty storeRepositoty) {
        this.storeRepositoty = storeRepositoty;
    }

    /**
     * 게시글 Regist API 에 대한 서비스
     *
     * @param insertInfo : 게시글 등록할 때 입력한 정보
     */
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

    /**
     *  점포 전체 조회 API에 대한 서비스
     */
    @Override
    public List<StoreFindRes> selectAllStore() {

        log.info("StoreService_findAll_start: ");

        List<StoreFindRes> res = storeRepositoty.findAll()
                .stream().map(m -> StoreFindRes.builder()
                        .stCode(m.getStCode())
                        .stImg(m.getStImg())
                        .stName(m.getStName())
                        .build()
                ).collect(Collectors.toList());

        log.info("StoreService_findAll_end: success");
        return res;
    }
}
