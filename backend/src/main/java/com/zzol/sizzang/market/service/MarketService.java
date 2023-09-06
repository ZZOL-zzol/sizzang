package com.zzol.sizzang.market.service;

import com.zzol.sizzang.market.dto.response.MarketSearchRes;

import java.util.List;

public interface MarketService {

    List<MarketSearchRes> searchMarket(String marketName, int limit, int offset);
}
