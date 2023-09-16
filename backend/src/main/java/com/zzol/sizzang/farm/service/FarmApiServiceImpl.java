package com.zzol.sizzang.farm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzol.sizzang.farm.dto.response.FarmAllRes;
import com.zzol.sizzang.farm.dto.response.FarmRegionalRes;
import com.zzol.sizzang.farm.dto.resquest.FarmApiReq;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class FarmApiServiceImpl implements FarmApiService {

    public FarmAllRes getRecentlyPrice(FarmApiReq farmApiReq){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/json; charset=UTF-8");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.kamis.or.kr/service/price/xml.do")
                .queryParam("p_cert_key", farmApiReq.getP_cert_key())
                .queryParam("p_cert_id", farmApiReq.getP_cert_id())
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
        FarmAllRes farmAllRes = null;

        try {
            farmAllRes = objMapper.readValue(response.getBody(), FarmAllRes.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return farmAllRes;

    }

    public FarmRegionalRes getRegionalPrice(FarmApiReq farmApiReq){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/json; charset=UTF-8");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.kamis.or.kr/service/price/xml.do?action=ItemInfo")
                .queryParam("p_cert_key", farmApiReq.getP_cert_key())
                .queryParam("p_cert_id", farmApiReq.getP_cert_id())
                .queryParam("p_countycode", farmApiReq.getP_countycode())
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
        FarmRegionalRes farmRegionalRes = null;

        try {
            farmRegionalRes = objMapper.readValue(response.getBody(), FarmRegionalRes.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return farmRegionalRes;
    }



}
