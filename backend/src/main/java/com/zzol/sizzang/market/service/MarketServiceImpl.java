package com.zzol.sizzang.market.service;

import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.market.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    private MarketRepository marketRepository;

    @Autowired
    public MarketServiceImpl(MarketRepository marketRepository){
        this.marketRepository = marketRepository;
    }

    @Override
    public List<MarketSearchRes> searchMarket(String marketName, int limit, int offset){
        marketRepository.searchMarket(marketName, limit, offset);
    }
}
