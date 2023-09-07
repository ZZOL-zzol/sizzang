package com.zzol.sizzang.market.service;

import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.market.entity.MarketEntity;
import com.zzol.sizzang.market.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {

    private MarketRepository marketRepository;

    @Autowired
    public MarketServiceImpl(MarketRepository marketRepository){
        this.marketRepository = marketRepository;
    }

    //시장상세
    @Override
    public Optional<MarketEntity> getMarketDetails(int mkCode) {
        return marketRepository.findByMkCode(mkCode);
    }

    //시장검색
    @Override
    public List<MarketSearchRes> searchMarket(String mkName, int limit, int offset){
        return marketRepository.searchMarketByName(mkName, limit, offset);
    }
}
