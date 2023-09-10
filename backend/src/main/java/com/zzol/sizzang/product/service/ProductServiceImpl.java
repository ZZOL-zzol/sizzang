package com.zzol.sizzang.product.service;

import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
//import com.zzol.sizzang.product.dto.response.ProductFindRes;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
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
//        if (file != null) {
//            log.info("ProductService_registProduct_start: " + registInfo.toString() + ", "
//                    + file);
//        } else {
//            log.info("ArticleService_registArticle_start: " + registInfo.toString());
//        }
        log.info("ArticleService_registArticle_start: " + registInfo.toString());
        //TODO 작성자 정보 가져오기
//        User user = userRepository.findById(registInfo.getUserId())
//                .orElseThrow(UserNotFoundException::new);

        int pcCode = registInfo.getPcCode();
        Long stCode = registInfo.getStCode();
        int pdCost = registInfo.getPdCost();
        String pdName = registInfo.getPdName();
        String pdIntro = registInfo.getPdIntro();
//        String pdImg = "";

        PrTagEntity prTagEntity = prTagRepository.findById(pcCode)
                .orElseThrow(NullPointerException::new);
        StoreEntity storeEntity = storeRepository.findById(stCode)
                .orElseThrow(NullPointerException::new);

//        //파일 저장
//        if (!Objects.isNull(file) && file.getSize() > 0) {
//            String imgPath = s3Service.saveFile(file);
//            pdImg = "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
//        }

        ProductEntity productEntity = ProductEntity.builder()
                .prTagEntity(prTagEntity)
                .pdIntro(pdIntro)
                .pdCost(pdCost)
                .pdName(pdName)
                .storeEntity(storeEntity)
//                .pdImg(pdImg)
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

        List<ProductFindRes> res = productRepository.findByStoreEntity_StCode(stCode)
                .stream().map(m -> ProductFindRes.builder()
                        .pdCode(m.getPdCode())
                        .pdCost(m.getPdCost())
//                        .pdImg(m.getPdImg())
                        .pdName(m.getPdName())
                        .build()
                ).collect(Collectors.toList());

        log.info("ProductService_findAll_end: success");
        return res;
    }

}
