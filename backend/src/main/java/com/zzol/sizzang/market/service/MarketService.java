package com.zzol.sizzang.market.service;

import com.zzol.sizzang.market.dto.request.MarketGetReq;
import com.zzol.sizzang.market.entity.MarketEntity;

import java.util.List;
import java.util.Optional;

public interface MarketService {

    //시장 상세
    Optional<MarketEntity> getMarketDetails(int mkCode);

    //시장 검색
    List<MarketEntity> searchMarket(MarketGetReq marketGetReq);

    List<MarketEntity> getAllMarket(MarketGetReq marketGetReq);


}
