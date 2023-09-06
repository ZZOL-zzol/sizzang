package com.zzol.sizzang.store.service;

import com.zzol.sizzang.common.exception.Template.StoreNotFoundException;
import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.dto.response.StoreSelectRes;
import com.zzol.sizzang.store.entity.StCategoryEntity;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StCategoryRepository;
import com.zzol.sizzang.store.repository.StoreRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.h2.mvstore.db.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class StoreServiceImpl implements StoreService{

    private final StCategoryRepository stCategoryRepository;
    private final StoreRepositoty storeRepositoty;

    @Autowired
    public StoreServiceImpl(StCategoryRepository stCategoryRepository, StoreRepositoty storeRepositoty) {
        this.stCategoryRepository = stCategoryRepository;
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

        StCategoryEntity stCategoryEntity = stCategoryRepository.findById(scCode)
                .orElseThrow(NullPointerException::new);

        StoreEntity storeEntity = StoreEntity.builder()
                .stCategoryEntity(stCategoryEntity)
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

    /**
     * 게시글 modify API 에 대한 서비스
     *
     * @param modifyInfo : 게시글 수정할 때 입력한 정보
     * @param fileList   : 게시글 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */
    @Override
    public boolean modifyStore(StoreModifyPutReq modifyInfo, List<MultipartFile> fileList) {

        if (fileList != null) {
            log.info("StoreService_modifyStore_start: " + modifyInfo.toString() + ", "
                    + fileList);
        } else {
            log.info("StoreService_modifyStore_start: " + modifyInfo.toString());
        }

        StoreEntity storeEntity = storeRepositoty.findById(modifyInfo.getStCode())
                .orElseThrow(StoreNotFoundException::new);
        // 현재 로그인 유저의 id와 글쓴이의 id가 일치할 때
//        if (storeEntity.getUser().getId().equals(modifyInfo.getUserId())) {
            // 게시글 수정


            log.info("StoreService_modifyStore_end: true");
            return true;
//        }
//        log.info("StoreService_modifyStore_end: false");
//        return false;
    }
    /**
     * 유저가 게시글의 상세 정보를 확인하기 위한 API 서비스
     *
     * @param stCode : 게시글의 Id
     */
    @Override
    public StoreSelectRes selectStore(Long stCode) {

        log.info("StoreService_findStore_start: " + stCode);

        StoreEntity storeEntity= storeRepositoty.findById(stCode)
                .orElseThrow(StoreNotFoundException::new);

        StoreSelectRes storeSelectRes = StoreSelectRes.builder()
                .stCode(stCode)
//                .userId(article.getUser().getId())
//                .author(article.getUser().getNickname())
                .stName(storeEntity.getStName())
                .stPhone(storeEntity.getStPhone())
                .stImg(storeEntity.getStImg())
                .stAccount(storeEntity.getStAccount())
                .stAccountHolder(storeEntity.getStAccountHolder())
                .stIntro(storeEntity.getStIntro())
                .stTime(storeEntity.getStTime())
                .build();

        // 게시글 상세 정보 조회 결과
        log.info("StoreService_findStore_end: " + storeSelectRes.toString());
        return storeSelectRes;
    }
}
