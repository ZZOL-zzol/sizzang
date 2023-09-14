package com.zzol.sizzang.market.service;

import com.zzol.sizzang.market.dto.request.MarketGetReq;
import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.repository.MarketRepository;
import com.zzol.sizzang.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {

    private MarketRepository marketRepository;
    private StoreRepository storeRepository;

    @Autowired
    public MarketServiceImpl(MarketRepository marketRepository,StoreRepository storeRepository){
        this.marketRepository = marketRepository;
        this.storeRepository = storeRepository;
    }

    //시장상세
    @Override
    public Optional<MarketEntity> getMarketDetails(int mkCode) {
        return marketRepository.findByMkCode(mkCode);
    }

    //시장검색
    @Override
    public List<MarketEntity> searchMarket(MarketGetReq marketGetReq){
        return marketRepository.searchMarketByName(marketGetReq.getMkName(), marketGetReq.getLimit(), marketGetReq.getOffset());
    }

    //시장 전체 불러오기
    @Override
    public List<MarketEntity> getAllMarket(MarketGetReq marketGetReq){
        return marketRepository.getAllMarket(marketGetReq.getLimit(), marketGetReq.getOffset());
    }


}
