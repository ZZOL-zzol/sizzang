package com.zzol.sizzang.product.service;

import com.zzol.sizzang.common.model.BaIResult;
import com.zzol.sizzang.product.dto.request.ProductModifyPutReq;
import com.zzol.sizzang.product.dto.request.ProductRegistInsertReq;
import com.zzol.sizzang.product.dto.response.ProductFindRes;
import com.zzol.sizzang.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity registProduct(ProductRegistInsertReq insertInfo);

    List<ProductFindRes> selectAllProduct(Long stCode);

    boolean modifyProduct(ProductModifyPutReq modifyInfo);

    BaIResult deleteProduct(Long stCode);

    List<ProductFindRes> findProductByTag (int tagCode);
}
