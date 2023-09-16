package com.zzol.sizzang.chatgpt.service;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.zzol.sizzang.chatgpt.model.ChatGptRequestDto;
import com.zzol.sizzang.chatgpt.model.response.ChatCompletion;
import com.zzol.sizzang.farm.dto.response.PriceRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class GptService {


    @Value("${apikey}")
    private String API_KEY;
    private static final String EDIT_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public List<String> recommendMenu(ChatGptRequestDto chatGptRequestDto) throws JsonProcessingException {
        List<String> ingredients = chatGptRequestDto.getIngredients();
        List<String> menus = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            List<ChatMessage> messages = new ArrayList<>();

            messages.add(new ChatMessage("user", ingredients.get(i) + "(을)를 사용하여 만들 수 있는 요리 하나만 추천해줘.필요한 재료설명을 포함해서 완전한 3문장으로만 대답해."));

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("messages", messages);
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("temperature", 0.0f);
            requestBody.put("max_tokens", 200);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(EDIT_ENDPOINT, requestEntity, Map.class);

            Map<String, Object> responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(responseBody);

            try {
                ObjectMapper objectMapper2 = new ObjectMapper();
                JsonNode jsonNode = objectMapper2.readTree(json);

                // "content" 필드 동적 추출
                String content = jsonNode.at("/choices/0/message/content").asText();
                menus.add(content);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return menus;
    }
}
