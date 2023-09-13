package com.zzol.sizzang.market.service;

import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.market.entity.MarketEntity;

import java.util.List;
import java.util.Optional;

public interface MarketService {

    //시장 상세
    Optional<MarketEntity> getMarketDetails(int mkCode);

    //시장 검색
    List<MarketSearchRes> searchMarket(String mkName, int limit, int offset);

//    double getMarketScore(int mkCode);

}
