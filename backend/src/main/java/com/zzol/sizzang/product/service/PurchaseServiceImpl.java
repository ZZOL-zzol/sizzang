package com.zzol.sizzang.product.service;

import com.zzol.sizzang.market.repository.MarketRepository;
import com.zzol.sizzang.product.dto.request.PurchaseDetailDto;
import com.zzol.sizzang.product.dto.request.PurchaseRegistRes;
import com.zzol.sizzang.product.entity.PurchaseDetailEntity;
import com.zzol.sizzang.product.entity.PurchaseEntity;
import com.zzol.sizzang.product.repository.PurchaseDetailRepository;
import com.zzol.sizzang.product.repository.PurchaseRepository;
import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.review.repository.ReviewRepository;
import com.zzol.sizzang.s3.service.FileService;
import com.zzol.sizzang.store.repository.StoreRepository;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,PurchaseDetailRepository purchaseDetailRepository){
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
    }


    //구매내역 등록하기
    @Override
    public void addPurchase(PurchaseRegistRes purchaseRegistRes){
        PurchaseEntity pu = new PurchaseEntity();
        pu.setStCode(purchaseRegistRes.getStCode());
        pu.setAccountNumber(purchaseRegistRes.getAccountNumber());
        pu.setPuCost(purchaseRegistRes.getPuCost());
        pu.setReRegisted(false);

        Long puCode = purchaseRepository.save(pu).getPuCode();

        for(int i=0; i<purchaseRegistRes.getDetails().size(); i++) {
            PurchaseDetailDto detailRes = purchaseRegistRes.getDetails().get(i);

            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setPuCnt(detailRes.getPuCnt());
            detailEntity.setPrName(detailRes.getPrName());
            detailEntity.setPrPrice(detailRes.getPrPrice());
            detailEntity.setPuCode(puCode);
            purchaseDetailRepository.save(detailEntity);
        }


    }//addPurchase

}

