package com.zzol.sizzang.farm.service;

import com.zzol.sizzang.farm.dto.response.PriceRes;
import com.zzol.sizzang.farm.entity.MarketPriceEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MarketPriceService {

    void getWholesalePrice();

    List<MarketPriceEntity> findCheaperItem(int direction);

}
