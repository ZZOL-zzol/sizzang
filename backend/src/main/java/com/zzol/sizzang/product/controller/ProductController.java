package com.zzol.sizzang.product.controller;

import com.zzol.sizzang.common.exception.Template.FileIOException;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
import com.zzol.sizzang.product.entity.ProductEntity;
import com.zzol.sizzang.product.service.ProductService;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.entity.StoreEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    private static final String SUCCESS = "success"; // API 성공 시 return

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Produt 등록
     */
    @Operation(description = "물품 등록 메서드입니다")
    @PostMapping
    public CommonResponse<?> insertProduct(@RequestPart ProductRegistInsertReq registInfo, @RequestPart(value = "file", required = false) MultipartFile file) {

        if (file != null) { // 게시물에 파일 있으면
            log.info("ProductController_regist_start: " + registInfo.toString() + ", "
                    + file);
        } else {
            log.info("ProductController_regist_start: " + registInfo.toString());
        }

        ProductEntity productEntity = productService.registProduct(registInfo, file);
        if (productEntity != null) {  // regist 성공하면 success
            log.info("ProductController_regist_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 실패하면 Exception
            throw new FileIOException();
        }
    }
}
