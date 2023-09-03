package com.zzol.sizzang.crawling.service;

import com.zzol.sizzang.crawling.entity.MarketPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketPriceService {
    private static String News_URL = "https://www.kamis.or.kr/customer/price/wholesale/catalogue.do?action=daily&regday=2023-09-01&countycode=1101&itemcategorycode=&convert_kg_yn=N";
    @PostConstruct
    public List<MarketPrice> getMarketPriceData() throws IOException{
        List<MarketPrice> marketPriceList = new ArrayList<>();
        Document document = Jsoup.connect(News_URL).get();
        Elements contents = document.select(".wtable3");
        for (Element content : contents) {
            MarketPrice marketPrice = MarketPrice.builder().build();
            System.out.println(content);
        }

        return marketPriceList;
    }
}
