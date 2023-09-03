package com.zzol.sizzang.crawling.controller;

import com.zzol.sizzang.crawling.entity.MarketPrice;
import com.zzol.sizzang.crawling.service.MarketPriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MarketPriceController {
    private final MarketPriceService marketPriceService;

    public MarketPriceController(MarketPriceService marketPriceService) {
        this.marketPriceService = marketPriceService;
    }

    @GetMapping("/marketprice")
    public void marketPrice(Model model) throws Exception{
        List<MarketPrice> marketPriceList = marketPriceService.getMarketPriceData();
    }
}
