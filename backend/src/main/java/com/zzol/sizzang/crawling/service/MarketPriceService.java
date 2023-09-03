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
    private static String[] Country_Code = {"1101", "2100", "2200", "2401", "2501"};
    @PostConstruct
    public void getMarketPriceData() throws IOException {
        for (String str : Country_Code) {
            String URL = "https://www.kamis.or.kr/customer/price/wholesale/catalogue.do?action=daily&regday=2023-09-01&countycode=" + str + "&itemcategorycode=&convert_kg_yn=N";
            System.out.println(str);
            List<MarketPrice> marketPriceList = new ArrayList<>();
            Document document = Jsoup.connect(URL).get();
            Elements contents = document.select(".wtable3");
            Elements trs = contents.select("tr");
            for (Element tr : trs) {
                MarketPrice marketPrice = MarketPrice.builder().build();
                System.out.println(tr);
                System.out.println("-----------------------------------------------------------------");
                Elements tds = trs.select("td");
            }

        }
    }
}
