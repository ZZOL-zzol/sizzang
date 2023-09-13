package com.zzol.sizzang.product.service;

import com.zzol.sizzang.common.exception.Template.StoreNotFoundException;
import com.zzol.sizzang.product.dto.response.PrTagFindRes;
import com.zzol.sizzang.product.entity.PrTagEntity;
import com.zzol.sizzang.product.repository.PrTagRepository;
import com.zzol.sizzang.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class PrTagServiceImpl implements PrTagService {

    private final ProductRepository productRepository;

    private final PrTagRepository prTagRepository;

    @Autowired
    public PrTagServiceImpl(ProductRepository productRepository, PrTagRepository prTagRepository) {
        this.productRepository = productRepository;
        this.prTagRepository = prTagRepository;
    }

    /*
     * 물품가격에 변경이 생길때 tag_cost값으 변경
     * */
    @Override
    public boolean modifyTagCost(int tagCode) {
        log.info("PrTagService_modifyTagCost_start: ");
        PrTagEntity prTagEntity = prTagRepository.findById(tagCode)
                .orElseThrow(StoreNotFoundException::new);

        List<PrTagFindRes> res = productRepository.findByPrTagEntity_TagCode(tagCode)
                .stream().map(m -> PrTagFindRes.builder()
                        .pdCost(m.getPdCost())
                        .tagCode(tagCode)
                        .build()
                ).collect(Collectors.toList());
        int tagCost = 0;
        for(PrTagFindRes p : res){
            tagCost += p.getPdCost();
            tagCost /= res.size();
        }

        prTagEntity.modifyTagCost(tagCost);


        log.info("PrTagService_modifyTagCost_end: true");
        return true;
    }
}
