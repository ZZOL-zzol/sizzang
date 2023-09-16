package com.zzol.sizzang.product.service;

import com.zzol.sizzang.product.dto.request.PurchaseRegistReq;
import com.zzol.sizzang.product.dto.response.PurchaseRegistRes;

import java.util.List;

public interface PurchaseService {

    void addPurchase(PurchaseRegistReq purchaseRegistReq);

    List<PurchaseRegistRes> getDetailList(String accountNumber);
}
