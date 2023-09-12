package com.zzol.sizzang.farm.service;

import org.springframework.http.ResponseEntity;

public interface MarketPriceService {

    ResponseEntity<String> getWholesalePrice();
}
