package com.zzol.sizzang.product.controller;

import com.zzol.sizzang.common.exception.Template.FileIOException;
import com.zzol.sizzang.common.exception.Template.StorePossessionFailException;
import com.zzol.sizzang.common.exception.Template.TemplateNoResultException;
import com.zzol.sizzang.common.exception.Template.TemplatePossessionFailException;
import com.zzol.sizzang.common.model.BaIResult;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.product.dto.request.ProductModifyPutReq;
import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
import com.zzol.sizzang.product.dto.response.ProductFindRes;
import com.zzol.sizzang.product.entity.ProductEntity;
import com.zzol.sizzang.product.service.PrTagService;
import com.zzol.sizzang.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    private static final String SUCCESS = "success"; // API 성공 시 return

    private final ProductService productService;

    private final PrTagService prTagService;

    @Autowired
    public ProductController(ProductService productService, PrTagService prTagService) {
        this.productService = productService;
        this.prTagService = prTagService;
    }

    /**
     * Produt 등록
     */
    @Operation(description = "물품 등록 메서드입니다")
    @PostMapping
    public CommonResponse<?> insertProduct(@RequestPart ProductRegistInsertReq registInfo) {

        log.info("ProductController_regist_start: " + registInfo.toString());
        ProductEntity productEntity = productService.registProduct(registInfo);

        //TODO : TAGCOST조정
        prTagService.modifyTagCost(registInfo.getTagCode());

        if (productEntity != null) {  // regist 성공하면 success
            log.info("ProductController_regist_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 실패하면 Exception
            throw new FileIOException();
        }
    }

    /**
     * Store별 물품 List 조회
     *
     * @return
     */
    @Operation(description = "물품 전체 조회 메서드입니다.")
    @GetMapping("/{stCode}")
    public CommonResponse<List<ProductFindRes>> findAll(@PathVariable Long stCode) {
        log.info("ProductController_findAll_start: ");

        Optional<List<ProductFindRes>> findRes = Optional.ofNullable(
                productService.selectAllProduct(stCode));
        //TODO : 평균가 같이 보내주기
        log.info("TemplateController_findAll_end: " + findRes);
        return CommonResponse.success(findRes.orElseThrow(TemplateNoResultException::new));
    }

    /**
     * 물품 modify API 에 대한 서비스
     *
     * @param modifyInfo : 점포 수정할 때 입력한 정보
     */
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public CommonResponse<?> modify(@RequestPart ProductModifyPutReq modifyInfo) {

        boolean isModified = productService.modifyProduct(modifyInfo);

        //TODO : TAGCOST조정
        prTagService.modifyTagCost(modifyInfo.getTagCode());
        if (isModified) {   // 수정 성공하면 success
            log.info("ArticleController_modify_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 수정 실패하면 Exception
            throw new StorePossessionFailException();
        }
    }


    /**
     * 물품 삭제하기 위한 API
     */
    @PutMapping("/delete/{pdCode}")
    public CommonResponse<?> delete(@PathVariable Long pdCode) {

        log.info("ProductController_delete_start: " + pdCode);

        BaIResult isDeleted = productService.deleteProduct(pdCode);

        //TODO : TAGCOST조정
        prTagService.modifyTagCost(isDeleted.getIntValue());
        if (isDeleted.isBooleanValue()) {    // 삭제 성공하면 success
            log.info("ProductController_delete_end: success");
            return CommonResponse.success(SUCCESS);
        } else {    // 삭제 실패하면 Exception
            throw new TemplatePossessionFailException();
        }
    }


    /**
     * tag별 물품 List 조회
     *
     * @return
     */
    @Operation(description = "태그별 물품 조회 메서드입니다.")
    @GetMapping("/prtag/{tagCode}")
    public CommonResponse<List<ProductFindRes>> findAll(@PathVariable int tagCode) {
        log.info("ProductController_findAll_start: ");

        Optional<List<ProductFindRes>> findRes = Optional.ofNullable(
                productService.findProductByTag(tagCode));

        log.info("ProductController_findAll_end: " + findRes);
        return CommonResponse.success(findRes.orElseThrow(TemplateNoResultException::new));
    }
}
