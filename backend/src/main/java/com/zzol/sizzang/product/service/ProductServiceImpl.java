package com.zzol.sizzang.product.service;

import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
import com.zzol.sizzang.product.entity.PdCategoryEntity;
import com.zzol.sizzang.product.entity.ProductEntity;
import com.zzol.sizzang.product.repository.PdCategoryRepository;
import com.zzol.sizzang.product.repository.ProductRepository;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.entity.StoreEntity;
import com.zzol.sizzang.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@Transactional
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    private final PdCategoryRepository pdCategoryRepository;

    private final StoreRepository storeRepository;

    private S3Service s3Service;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PdCategoryRepository pdCategoryRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.pdCategoryRepository = pdCategoryRepository;
        this.storeRepository = storeRepository;
    }

    /**
     * 게시글 Regist API 에 대한 서비스
     *
     * @param registInfo : 게시글 등록할 때 입력한 정보
     * @param file   : 게시글 사진, 게시글에는 사진이 반드시 있을 필요가 없음
     */
    @Override
    public ProductEntity registProduct(ProductRegistInsertReq registInfo, MultipartFile file){
        if (file != null) {
            log.info("ProductService_registProduct_start: " + registInfo.toString() + ", "
                    + file);
        } else {
            log.info("ArticleService_registArticle_start: " + registInfo.toString());
        }
        //TODO 작성자 정보 가져오기
//        User user = userRepository.findById(registInfo.getUserId())
//                .orElseThrow(UserNotFoundException::new);

        int pcCode = registInfo.getPcCode();
        Long stCode = registInfo.getStCode();
        int pdCost = registInfo.getPdCost();
        String pdName = registInfo.getPdName();
        String pdIntro = registInfo.getPdIntro();
        String pdImg = "";

        PdCategoryEntity pdCategoryEntity = pdCategoryRepository.findById(pcCode)
                .orElseThrow(NullPointerException::new);
        StoreEntity storeEntity = storeRepository.findById(stCode)
                .orElseThrow(NullPointerException::new);

        //파일 저장
        if (!Objects.isNull(file) && file.getSize() > 0) {
            String imgPath = s3Service.saveFile(file);
            pdImg = "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
        }

        ProductEntity productEntity = ProductEntity.builder()
                .pdCategoryEntity(pdCategoryEntity)
                .pdIntro(pdIntro)
                .pdCost(pdCost)
                .pdName(pdName)
                .storeEntity(storeEntity)
                .pdImg(pdImg)
                .build();
        productRepository.save(productEntity);

        log.info("ProductService_insertProduct_end: success");
        return productEntity;
    }
}
