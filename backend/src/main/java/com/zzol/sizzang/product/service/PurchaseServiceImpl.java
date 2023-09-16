package com.zzol.sizzang.product.service;

import com.zzol.sizzang.product.dto.request.PurchaseDetailDto;
import com.zzol.sizzang.product.dto.request.PurchaseRegistReq;
import com.zzol.sizzang.product.dto.response.PurchaseRegistRes;
import com.zzol.sizzang.product.entity.PurchaseDetailEntity;
import com.zzol.sizzang.product.entity.PurchaseEntity;
import com.zzol.sizzang.product.repository.PurchaseDetailRepository;
import com.zzol.sizzang.product.repository.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void addPurchase(PurchaseRegistReq purchaseRegistReq){
        PurchaseEntity pu = new PurchaseEntity();
        pu.setStCode(purchaseRegistReq.getStCode());
        pu.setAccountNumber(purchaseRegistReq.getAccountNumber());
        pu.setPuCost(purchaseRegistReq.getPuCost());
        pu.setReRegisted(false);

        Long puCode = purchaseRepository.save(pu).getPuCode();

        for(int i=0; i<purchaseRegistReq.getDetails().size(); i++) {
            PurchaseDetailDto detailRes = purchaseRegistReq.getDetails().get(i);

            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            detailEntity.setPuCnt(detailRes.getPuCnt());
            detailEntity.setPrName(detailRes.getPrName());
            detailEntity.setPrPrice(detailRes.getPrPrice());
            detailEntity.setPuCode(puCode);
            purchaseDetailRepository.save(detailEntity);
        }


    }//addPurchase

    @Override
    public List<PurchaseRegistRes> getDetailList(String accountNumber) {
        List<PurchaseEntity> puList = purchaseRepository.findByAccountNumber(accountNumber);
        List<PurchaseRegistRes> puResList = new ArrayList<>();
        for(PurchaseEntity pu : puList) {
            PurchaseRegistRes puRes = new PurchaseRegistRes();
            List<PurchaseDetailEntity> detailList = purchaseDetailRepository.findAllByPuCode(pu.getPuCode());

            puRes.setPuCode(pu.getPuCode());
            puRes.setReRegisted(pu.getReRegisted());
            puRes.setStCode(pu.getStCode());
            puRes.setPuCost(pu.getPuCost());
            puRes.setPuDate(pu.getPuDate().toString());
            puRes.setAccountNumber(pu.getAccountNumber());
            puRes.setDetails(detailList);

            puResList.add(puRes);
        }

        return puResList;
    }//getDetailList

}

