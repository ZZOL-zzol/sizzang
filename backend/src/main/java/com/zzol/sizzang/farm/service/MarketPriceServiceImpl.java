package com.zzol.sizzang.farm.service;

import com.zzol.sizzang.farm.entity.MarketPriceEntity;
import com.zzol.sizzang.farm.dto.response.PriceRes;
import com.zzol.sizzang.farm.repository.MarketPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class MarketPriceServiceImpl implements MarketPriceService {

    private MarketPriceRepository marketPriceRepository;

    @Autowired
    public MarketPriceServiceImpl(MarketPriceRepository marketPriceRepository){
        this.marketPriceRepository = marketPriceRepository;
    }


    @Override
    @Scheduled(cron = "0 0 0/12 1/1 * ?")
    public void getWholesalePrice(){

        marketPriceRepository.deleteAllInBatch();

        //요청해서 응답받기
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/json; charset=UTF-8");
        //headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.kamis.or.kr/service/price/xml.do")
//                .queryParam("action", "dailyPriceByCategoryList")
//                .queryParam("p_product_cls_code", "02")
//                .queryParam("p_regday", "2023-09-12")
//                .queryParam("p_convert_kg_yn", "N")
//                .queryParam("p_item_category_code", "100")
                .queryParam("p_cert_key", "9ec7751a-6865-4116-98d5-181afba8c407")
                .queryParam("p_cert_id", "minsik0156@gmail.com")
                .queryParam("p_returntype", "json");

        HttpEntity<String> httpEntity =
                new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        //객체로 변환
        ObjectMapper objMapper = new ObjectMapper();
        PriceRes priceRes = null;

        try {
            priceRes = objMapper.readValue(response.getBody(), PriceRes.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //prices 돌면서 저장하기
        for(int i = 0; i<priceRes.getPrice().size(); i++){

            MarketPriceEntity item = new MarketPriceEntity();

            item.setConditionStr(""+priceRes.getCondition().toString());
            item.setProductClsCode(""+priceRes.getPrice().get(i).getProduct_cls_code().toString());
            item.setProductClsName(""+priceRes.getPrice().get(i).getProduct_cls_name().toString());
            item.setCategoryName(""+priceRes.getPrice().get(i).getCategory_name().toString());
            item.setLastestDay(""+priceRes.getPrice().get(i).getLastest_day().toString());
            String[] part = priceRes.getPrice().get(i).getProductName().toString().split("/");
            item.setProductName(""+part[0]);
            item.setItemName(""+priceRes.getPrice().get(i).getItem_name().toString());
            String unitStr = priceRes.getPrice().get(i).getUnit().replaceAll("\\d", "");
            int unitInt = Integer.parseInt(priceRes.getPrice().get(i).getUnit().replaceAll("\\D", ""));
            item.setUnit(unitStr);
            item.setDirection(Integer.parseInt(priceRes.getPrice().get(i).getDirection().toString()));
            item.setValue(Double.parseDouble(priceRes.getPrice().get(i).getValue().toString()));
            try{
                item.setDpr1(Integer.parseInt(""+priceRes.getPrice().get(i).getDpr1().toString().replaceAll(",","")) / unitInt);
            }catch(Exception e){
                item.setDpr1(0);
            };
            try{
                item.setDpr2(Integer.parseInt(""+priceRes.getPrice().get(i).getDpr2().toString().replaceAll(",","")) / unitInt);
            }catch(Exception e){
                item.setDpr2(0);
            };
            try{
                item.setDpr3(Integer.parseInt(""+priceRes.getPrice().get(i).getDpr3().toString().replaceAll(",","")) / unitInt);
            }catch(Exception e){
                item.setDpr3(0);
            };
            try{
                item.setDpr4(Integer.parseInt(priceRes.getPrice().get(i).getDpr4().toString().replaceAll(",","")) / unitInt);
            }catch(Exception e){
                item.setDpr4(0);
            };

            marketPriceRepository.save(item);
        }

    }

    @Override
    public List<MarketPriceEntity> findCheaperItem(int direction){
        List<MarketPriceEntity> items = marketPriceRepository.findByDirectionOrderByValueDesc(direction);

        List<MarketPriceEntity> resultItems = new ArrayList<>();
        for(int i= 0; i<5; i++) {
            resultItems.add(items.get(i));
        }
        return resultItems;
    }


}
