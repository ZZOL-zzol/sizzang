package com.zzol.sizzang.chatgpt.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.zzol.sizzang.chatgpt.model.RequestQuestionVo;
import com.zzol.sizzang.chatgpt.model.RequestRecommFoodVo;
import com.zzol.sizzang.chatgpt.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GptService {


    @Value("${apikey}")
    private String API_KEY;
    private static final String EDIT_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public String recommendMenu(String prompt) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        List<ChatMessage> messages = new ArrayList<>();

        messages.add(new ChatMessage("user", prompt+"(을)를 사용하여 만들 수 있는 메뉴 하나만 추천해줘. 3문장으로 완성해서 대답해."));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", messages);
        requestBody.put("model","gpt-3.5-turbo");
        requestBody.put("temperature", 0.0f);
        requestBody.put("max_tokens", 200);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(EDIT_ENDPOINT, requestEntity, Map.class);
        Map<String, Object> responseBody = response.getBody();

        try {
            // ObjectMapper를 사용하여 Map을 JSON 문자열로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseBody);

            return jsonResponse;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return "Error: Failed to convert responseBody to JSON";
        }


//        return responseBody.toString();
    }
}
