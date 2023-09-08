package com.zzol.sizzang.store.service;

import com.zzol.sizzang.common.exception.Template.StoreNotFoundException;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.dto.response.StoreSelectRes;
import com.zzol.sizzang.store.entity.StCategoryEntity;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StCategoryRepository;
import com.zzol.sizzang.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class StoreServiceImpl implements StoreService{

    private final StCategoryRepository stCategoryRepository;
    private final StoreRepository storeRepository;

    private S3Service s3Service;

    @Autowired
    public StoreServiceImpl(StCategoryRepository stCategoryRepository, StoreRepository storeRepository) {
        this.stCategoryRepository = stCategoryRepository;
        this.storeRepository = storeRepository;
    }

    /**
     * 게시글 Regist API 에 대한 서비스
     *
     * @param registInfo : 게시글 등록할 때 입력한 정보
     * @param fileList   : 게시글 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */
    @Override
    public StoreEntity registStore(StoreRegistInsertReq registInfo, MultipartFile fileList) {
        if (fileList != null) {
            log.info("ArticleService_registArticle_start: " + registInfo.toString() + ", "
                    + fileList);
        } else {
            log.info("ArticleService_registArticle_start: " + registInfo.toString());
        }
        //TODO 작성자 정보 가져오기
//        User user = userRepository.findById(registInfo.getUserId())
//                .orElseThrow(UserNotFoundException::new);

        int scCode = registInfo.getScCode();
        String stName = registInfo.getStName();
        String stPhone = registInfo.getStPhone();
        String stImg = "";
        String stAccount = registInfo.getStAccount();
        String stAccountHolder = registInfo.getStAccountHolder();
        String stIntro = registInfo.getStIntro();
        String stTime = registInfo.getStTime();

        StCategoryEntity stCategoryEntity = stCategoryRepository.findById(scCode)
                .orElseThrow(NullPointerException::new);

        //파일 저장
        if (!Objects.isNull(fileList) && fileList.getSize() > 0) {
            String imgPath = s3Service.saveFile(fileList);
            stImg = "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
        }


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

        storeRepository.save(storeEntity);


        log.info("StoreService_insertStore_end: success");
        return storeEntity;
    }

    /**
     *  점포 전체 조회 API에 대한 서비스
     */
    @Override
    public List<StoreFindRes> selectAllStore() {

        log.info("StoreService_findAll_start: ");

        List<StoreFindRes> res = storeRepository.findAll()
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
     *  게시글 검색어로 검색하여 조회 API에 대한 서비스
     */
    @Override
    public List<StoreFindRes> findByCondition(
            FindByConditionGetReq findByConditionGetReq) {

        log.info("StoreService_findByCondition_start: " + findByConditionGetReq.toString());

        List<StoreFindRes> res = storeRepository.findByCondition(findByConditionGetReq);

        log.info("StoreService_findByConditionTemplate_end: success");
        return res;
    }

    /**
     * 게시글 modify API 에 대한 서비스
     *
     * @param modifyInfo : 게시글 수정할 때 입력한 정보
     * @param fileList   : 게시글 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */
    @Override
    public boolean modifyStore(StoreModifyPutReq modifyInfo, MultipartFile fileList) {

        if (fileList != null) {
            log.info("StoreService_modifyStore_start: " + modifyInfo.toString() + ", "
                    + fileList);
        } else {
            log.info("StoreService_modifyStore_start: " + modifyInfo.toString());
        }

        StoreEntity storeEntity = storeRepository.findById(modifyInfo.getStCode())
                .orElseThrow(StoreNotFoundException::new);
        // 현재 로그인 유저의 id와 글쓴이의 id가 일치할 때
//        if (storeEntity.getUser().getId().equals(modifyInfo.getUserId())) {
            // 점포 수정
            StCategoryEntity stCategoryEntity = stCategoryRepository.findById((modifyInfo.getScCode())).orElseThrow(NullPointerException::new);
            storeEntity.modifyStore(stCategoryEntity, modifyInfo.getStName(), modifyInfo.getStPhone(), modifyInfo.getStImg(), modifyInfo.getStIntro(), modifyInfo.getStTime());

            log.info("StoreService_modifyStore_end: true");
            return true;
//        }
//        log.info("StoreService_modifyStore_end: false");
//        return false;
    }
    /**
     * 유저가 점포의 상세 정보를 확인하기 위한 API 서비스
     *
     * @param stCode : 점포의 Id
     */
    @Override
    public StoreSelectRes selectStore(Long stCode) {

        log.info("StoreService_findStore_start: " + stCode);

        StoreEntity storeEntity= storeRepository.findById(stCode)
                .orElseThrow(StoreNotFoundException::new);

        StoreSelectRes storeSelectRes = StoreSelectRes.builder()
                .stCode(stCode)
                //TODO : user 연동
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
