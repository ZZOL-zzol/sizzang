package com.zzol.sizzang.product.service;

import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
import com.zzol.sizzang.product.dto.response.ProductFindRes;
import com.zzol.sizzang.product.entity.ProductEntity;
import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductEntity registProduct(ProductRegistInsertReq insertInfo, MultipartFile file);

    List<ProductFindRes> selectAllProduct(Long stCode);
//
//    boolean modifyProduct(StoreModifyPutReq modifyInfo, MultipartFile file);
}
