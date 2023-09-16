package com.zzol.sizzang.product.service;

import com.zzol.sizzang.common.exception.Template.StoreNotFoundException;
import com.zzol.sizzang.product.dto.response.PrTagCostFindRes;
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

        List<PrTagCostFindRes> res = productRepository.findByPrTagEntity_TagCode(tagCode)
                .stream().map(m -> PrTagCostFindRes.builder()
                        .pdCost(m.getPdCost())
                        .tagCode(tagCode)
                        .build()
                ).collect(Collectors.toList());
        int tagCost = 0;
        for(PrTagCostFindRes p : res){
            tagCost += p.getPdCost();
        }
        tagCost /= res.size();

        prTagEntity.modifyTagCost(tagCost);


        log.info("PrTagService_modifyTagCost_end: true");
        return true;
    }

    @Override
    public List<PrTagFindRes> findTagByCategory(int pcCode) {
        log.info("PrTagService_findTagByCategory_start: ");

        List<PrTagFindRes> res = prTagRepository.findByPdCategoryEntity_PcCode(pcCode)
                .stream().map(m -> PrTagFindRes.builder()
                        .tagName(m.getTagName())
                        .tagUnit(m.getTagUnit())
                        .tagCost(m.getTagCost())
                        .tagCode(m.getTagCode())
                        .build()
                ).collect(Collectors.toList());

        log.info("PrTagService_findTagByCategory_end: true");
        return res;
    }

    @Override
    public List<PrTagFindRes> findAll() {
        log.info("PrTagService_findAll_start: ");

        List<PrTagFindRes> res = prTagRepository.findAll()
                .stream().map(m -> PrTagFindRes.builder()
                        .tagName(m.getTagName())
                        .tagUnit(m.getTagUnit())
                        .tagCost(m.getTagCost())
                        .tagCode(m.getTagCode())
                        .build()
                ).collect(Collectors.toList());

        log.info("PrTagService_findAll_end: true");
        return res;
    }


}
