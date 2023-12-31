package com.zzol.sizzang.store.service;

import com.zzol.sizzang.common.exception.Template.NoDataException;
import com.zzol.sizzang.common.exception.Template.StoreNotFoundException;
import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.repository.MarketRepository;
import com.zzol.sizzang.product.entity.ProductEntity;
import com.zzol.sizzang.product.repository.ProductRepository;
import com.zzol.sizzang.review.repository.ReviewRepository;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindByUserRes;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.dto.response.StoreSelectRes;
import com.zzol.sizzang.store.entity.StCategoryEntity;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StCategoryRepository;
import com.zzol.sizzang.store.repository.StoreRepository;
import com.zzol.sizzang.user.entity.User;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class StoreServiceImpl implements StoreService{

    private final StCategoryRepository stCategoryRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MarketRepository marketRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private S3Service s3Service;

    @Autowired
    public StoreServiceImpl(StCategoryRepository stCategoryRepository, StoreRepository storeRepository, ReviewRepository reviewRepository, MarketRepository marketRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.stCategoryRepository = stCategoryRepository;
        this.storeRepository = storeRepository;
        this.reviewRepository = reviewRepository;
        this.marketRepository = marketRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public class StoreFindResComparator implements Comparator<StoreFindRes> {
        @Override
        public int compare(StoreFindRes res1, StoreFindRes res2) {
            // reScore를 내림차순으로 정렬
            return Double.compare(res2.getReScore(), res1.getReScore());
        }
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
        User user = userRepository.findById(registInfo.getUserCode())
                .orElseThrow(NullPointerException::new);
        MarketEntity marketEntity = marketRepository.findById(registInfo.getMkCode())
                .orElseThrow(NullPointerException::new);

        int scCode = registInfo.getScCode();
        String stName = registInfo.getStName();
        String stPhone = registInfo.getStPhone();
        String stImg = "";
        String stAccount = user.getUserAccount();
        String stAccountHolder = user.getUserName();
        String stIntro = registInfo.getStIntro();
        String stTime = registInfo.getStTime();
        String stLatitude = registInfo.getStLatitude();
        String stLongtitude = registInfo.getStLongtitude();
        int mkCode = registInfo.getMkCode();
        String stAddress = registInfo.getStAddress();

        StCategoryEntity stCategoryEntity = stCategoryRepository.findById(scCode)
                .orElseThrow(NullPointerException::new);

        //파일 저장
        if (!Objects.isNull(fileList) && fileList.getSize() > 0) {
            String imgPath = s3Service.saveFile(fileList);
            stImg = "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
        }


        StoreEntity storeEntity = StoreEntity.builder()
                .stCategoryEntity(stCategoryEntity)
                .user(user)
                .marketEntity(marketEntity)
                .stName(stName)
                .stPhone(stPhone)
                .stImg(stImg)
                .stAccount(stAccount)
                .stAccountHolder(stAccountHolder)
                .stIntro(stIntro)
                .stTime(stTime)
                .stLatitude(stLatitude)
                .stLongtitude(stLongtitude)
                .stAddress(stAddress)
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
                        .mkCode(m.getMarketEntity().getMkCode())
                        .reCnt(reviewRepository.findByStCode(m.getStCode()).size())
                        .reScore((reviewRepository.findByStCode(m.getStCode()).size()==0)?0:reviewRepository.getReviewScore(m.getStCode()))
                        .stCode(m.getStCode())
                        .stImg(m.getStImg())
                        .stName(m.getStName())
                        .stLatitude(m.getStLatitude())
                        .stLongtitude(m.getStLongtitude())
                        .stAddress(m.getStAddress())
                        .scName(stCategoryRepository.findByScCode(m.getStCategoryEntity().getScCode()).get().getScName())
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
        for(StoreFindRes sr : res){
            sr.setReCnt(reviewRepository.findByStCode(sr.getStCode()).size());
            sr.setReScore((reviewRepository.findByStCode(sr.getStCode()).size()==0)?0:reviewRepository.getReviewScore(sr.getStCode()));
        }

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
            storeEntity.modifyStore(stCategoryEntity, modifyInfo.getStName(), modifyInfo.getStPhone(), modifyInfo.getStImg(), modifyInfo.getStIntro(), modifyInfo.getStTime(), modifyInfo.getStLatitude(), modifyInfo.getStLongtitude());
//          TODO : 사진 어떻게 처리할지 관리
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
                .stOwner(storeEntity.getUser().getUserName())
                .stName(storeEntity.getStName())
                .stPhone(storeEntity.getStPhone())
                .stImg(storeEntity.getStImg())
                .stAccount(storeEntity.getStAccount())
                .stAccountHolder(storeEntity.getStAccountHolder())
                .stIntro(storeEntity.getStIntro())
                .stTime(storeEntity.getStTime())
                .stScore(storeEntity.getStScore())
                .stLatitude(storeEntity.getStLatitude())
                .stLongtitude(storeEntity.getStLongtitude())
                .stAddress(storeEntity.getStAddress())
                .scName(stCategoryRepository.findByScCode(storeEntity.getStCategoryEntity().getScCode()).get().getScName())
                .mkCode(storeEntity.getMarketEntity().getMkCode())
                .build();

        // 게시글 상세 정보 조회 결과
        log.info("StoreService_findStore_end: " + storeSelectRes.toString());
        return storeSelectRes;
    }

    /**
     * 점포 삭제 (Soft Delete) API 에 대한 서비스
     */
    @Override
    public Boolean deleteStore(Long stCode) {

        log.info("StoreService_deleteStore_start: ");

        StoreEntity storeEntity = storeRepository.findById(stCode)
                .orElseThrow(NoDataException::new);

        // 실제 서비스에서는 article을 작성한 유저와 삭제 요청을 한 유저를 비교해서 둘이 같을 경우에만 삭제가 되도록.
        // 둘을 비교해서 두 유저 정보가 다를 경우 false 를 리턴하면 됨
        storeEntity.deleteTemplate();
        log.info("StoreService_deleteStore_end: true");
        return true;
    }

    @Override
    public double getStoreScore(long stCode) {
        return reviewRepository.getReviewScore(stCode);
    }

    /**
     *  시장별 점포 조회 API에 대한 서비스
     */
    @Override
    public List<StoreFindRes> selectAllStoreByMarket(int mkCode) {

        log.info("StoreService_findAllByMarket_start: ");

        List<StoreFindRes> res = storeRepository.findByMarketEntity_MkCode(mkCode)
                .stream().map(m -> StoreFindRes.builder()
//                                .mkCode(m.getMarketEntity().getMkCode())
                                .stIntro(m.getStIntro())
                                .reCnt(reviewRepository.findByStCode(m.getStCode()).size())
                                .reScore((reviewRepository.findByStCode(m.getStCode()).size()==0)?0:reviewRepository.getReviewScore(m.getStCode()))
                                .stCode(m.getStCode())
                                .stImg(m.getStImg())
                                .stName(m.getStName())
                                .stLatitude(m.getStLatitude())
                                .stLongtitude(m.getStLongtitude())
                                .stAddress(m.getStAddress())
                                .scName(stCategoryRepository.findByScCode(m.getStCategoryEntity().getScCode()).get().getScName())
                                .build()
                ).collect(Collectors.toList());

        log.info("StoreService_findAllByMarket_end: success");
        return res;
    }

    /**
     *  유저별 점포 조회 API에 대한 서비스
     */
    @Override
    public List<StoreFindByUserRes> findByStoreByUser(Long userCode) {
        log.info("StoreService_findByStoreByUser_start: ");
        List<StoreEntity> r = storeRepository.findByUser_UserCode(userCode);
        System.out.println(r.size());
        for(StoreEntity p : r){
            System.out.println(p.getStName());
        }

        List<StoreFindByUserRes> res = storeRepository.findByUser_UserCode(userCode)
                .stream().map(m -> StoreFindByUserRes.builder()
                        .stName(m.getStName())
                        .mkName(m.getMarketEntity().getMkName())
                        .mkAddress(m.getMarketEntity().getMkAddress())
                        .stAddress(m.getStAddress())
                        .scName(stCategoryRepository.findByScCode(m.getStCategoryEntity().getScCode()).get().getScName())
                        .stPhone(m.getStPhone())
                        .stTime(m.getStTime())
                        .stIntro(m.getStIntro())
                        .build()
                ).collect(Collectors.toList());

        log.info("StoreService_findByStoreByUser_end: success");
        return res;
    }


    @Override
    public List<StoreFindRes> findStoreCodeByTag(int tagCode) {
        log.info("ProductService_findProductCodeByTag_start: ");

        List<ProductEntity> pe = productRepository.findByPrTagEntity_TagCode(tagCode);
        List<StoreEntity> stores = new ArrayList<>();
        for(ProductEntity p : pe){
            stores.add(p.getStoreEntity());
        }
        List<StoreFindRes> res = stores.stream().map(m -> StoreFindRes.builder()
                .mkCode(m.getMarketEntity().getMkCode())
                .reCnt(reviewRepository.findByStCode(m.getStCode()).size())
                .reScore((reviewRepository.findByStCode(m.getStCode()).size()==0)?0:reviewRepository.getReviewScore(m.getStCode()))
                .stCode(m.getStCode())
                .stImg(m.getStImg())
                .stName(m.getStName())
                .stLatitude(m.getStLatitude())
                .stLongtitude(m.getStLongtitude())
                .stAddress(m.getStAddress())
                .scName(stCategoryRepository.findByScCode(m.getStCategoryEntity().getScCode()).get().getScName())
                .build()
        ).collect(Collectors.toList());

        Collections.sort(res, new StoreFindResComparator());
        log.info("ProductService_findProductCodeByTag_end: true");
        return res;
    }
}
