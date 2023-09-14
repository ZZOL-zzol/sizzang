package com.zzol.sizzang.product.service;

import com.zzol.sizzang.common.exception.Template.NoDataException;
import com.zzol.sizzang.common.exception.Template.StoreNotFoundException;
import com.zzol.sizzang.common.model.BaIResult;
import com.zzol.sizzang.product.dto.request.ProductModifyPutReq;
import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
import com.zzol.sizzang.product.dto.response.ProductFindRes;
import com.zzol.sizzang.product.entity.PrTagEntity;
import com.zzol.sizzang.product.entity.ProductEntity;
import com.zzol.sizzang.product.repository.PdCategoryRepository;
import com.zzol.sizzang.product.repository.PrTagRepository;
import com.zzol.sizzang.product.repository.ProductRepository;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    private final PrTagRepository prTagRepository;

    private final StoreRepository storeRepository;

    private S3Service s3Service;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PdCategoryRepository pdCategoryRepository, PrTagRepository prTagRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.prTagRepository = prTagRepository;
        this.storeRepository = storeRepository;
    }

    /**
     * 점포 Regist API 에 대한 서비스
     *
     * @param registInfo : 게시글 등록할 때 입력한 정보
//     * @param file   : 게시글 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */
    @Override
    public ProductEntity registProduct(ProductRegistInsertReq registInfo){

        log.info("ArticleService_registArticle_start: " + registInfo.toString());
        //TODO 작성자 정보 가져오기
//        User user = userRepository.findById(registInfo.getUserId())
//                .orElseThrow(UserNotFoundException::new);

        int tagCode = registInfo.getTagCode();
        Long stCode = registInfo.getStCode();
        int pdCost = registInfo.getPdCost();
        String pdName = registInfo.getPdName();
        String pdIntro = registInfo.getPdIntro();

        PrTagEntity prTagEntity = prTagRepository.findById(tagCode)
                .orElseThrow(NullPointerException::new);
        StoreEntity storeEntity = storeRepository.findById(stCode)
                .orElseThrow(NullPointerException::new);

        ProductEntity productEntity = ProductEntity.builder()
                .prTagEntity(prTagEntity)
                .pdIntro(pdIntro)
                .pdCost(pdCost)
                .pdName(pdName)
                .storeEntity(storeEntity)
                .build();
        productRepository.save(productEntity);

        log.info("ProductService_insertProduct_end: success");
        return productEntity;
    }

    /**
     *  물품 전체 조회 API에 대한 서비스
     */
    @Override
    public List<ProductFindRes> selectAllProduct(Long stCode) {

        log.info("ProductService_findAll_start: ");
        //TODO : TAGCOST추가

        List<ProductFindRes> res = productRepository.findByStoreEntity_StCode(stCode)
                .stream().map(m -> ProductFindRes.builder()
                        .stName(m.getStoreEntity().getStName())
                        .pdCode(m.getPdCode())
                        .pdCost(m.getPdCost())
                        .pdIntro(m.getPdIntro())
                        .pdName(m.getPdName())
                        .tagCost(m.getPrTagEntity().getTagCost())
                        .tagName(m.getPrTagEntity().getTagName())
                        .build()
                ).collect(Collectors.toList());

        log.info("ProductService_findAll_end: success");
        return res;
    }

    /**
     * 물품 modify API 에 대한 서비스
     *
     * @param modifyInfo : 물품 수정할 때 입력한 정보
     */
    @Override
    public boolean modifyProduct(ProductModifyPutReq modifyInfo) {
        log.info("ProductService_modifyProduct_start: ");
        ProductEntity productEntity = productRepository.findById(modifyInfo.getPdCode())
                .orElseThrow(StoreNotFoundException::new);
        // 현재 로그인 유저의 id와 글쓴이의 id가 일치할 때
//        if (storeEntity.getUser().getId().equals(modifyInfo.getUserId())) {
        PrTagEntity prTagEntity = prTagRepository.findById((modifyInfo.getTagCode()))
                .orElseThrow(NullPointerException::new);
        StoreEntity storeEntity = storeRepository.findById((modifyInfo.getStCode()))
                .orElseThrow(NullPointerException::new);
        productEntity.modifyProduct(storeEntity, prTagEntity, modifyInfo.getPdName(), modifyInfo.getPdIntro(), modifyInfo.getPdCost());
//          TODO : 사진 어떻게 처리할지 관리
        log.info("ProductService_modifyProduct_end: true");
        return true;
//        }
//        log.info("StoreService_modifyStore_end: false");
//        return false;
    }

    /**
     * 물품 삭제 (Soft Delete) API 에 대한 서비스
     */
    @Override
    public BaIResult deleteProduct(Long pdCode) {

        log.info("ProductService_deleteProduct_start: ");

        ProductEntity productEntity = productRepository.findById(pdCode)
                .orElseThrow(NoDataException::new);
        // 실제 서비스에서는 article을 작성한 유저와 삭제 요청을 한 유저를 비교해서 둘이 같을 경우에만 삭제가 되도록.
        // 둘을 비교해서 두 유저 정보가 다를 경우 false 를 리턴하면 됨
        productEntity.deleteTemplate();
        log.info("ProductService_deleteProduct_end: true");
        return new BaIResult(true,productEntity.getPrTagEntity().getTagCode() );
    }

    /**
     * tag를 통한 물품검색 API 서비스
     */
    @Override
    public List<ProductFindRes> findProductByTag(int tagCode) {
        log.info("ProductService_findProductByTag_start: ");

        List<ProductFindRes> res = productRepository.findByPrTagEntity_TagCode(tagCode)
                .stream().map(m -> ProductFindRes.builder()
                        .pdCost(m.getPdCost())
                        .tagCost(m.getPrTagEntity().getTagCost())
                        .pdIntro(m.getPdIntro())
                        .pdName(m.getPdName())
                        .stName(m.getStoreEntity().getStName())
                        .pdCode(m.getPdCode())
                        .tagName(m.getPrTagEntity().getTagName())
                        .build()
                ).collect(Collectors.toList());

        log.info("ProductService_findProductByTag_end: true");
        return res;
    }
}
