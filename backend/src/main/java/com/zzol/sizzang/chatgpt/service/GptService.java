package com.zzol.sizzang.chatgpt.service;


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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@PropertySource("classpath:apikey.yml")
public class GptService {

    @Value("${apikey}")
    private String API_KEY;
    private static final String COMPLETION_ENDPOINT = "https://api.openai.com/v1/completions";

    public ResponseVo getRecommendFood(RequestRecommFoodVo requestRecommFoodVo) {
        ResponseVo responseVo = new ResponseVo();
        try {
            log.info("재료 :" + requestRecommFoodVo.getText());
//            String question = requestRecommFoodVo.getText() + "(을)를 재료로 만들 수 있는 음식은 무엇이 있을까요? ";
            String question = "Recommend foods that can be made with " + requestRecommFoodVo.getText();
            System.out.println(question);
            String answer = generateText(question, 0.0f, 20);

            String answerFilter1 = answer.replaceAll("\n", "");
            String result =  answerFilter1.replaceAll("\\.","");
            responseVo.setCode(200);
            responseVo.setResponse(result);

        } catch (Exception e) {
            responseVo.setCode(500);
            responseVo.setResponse("generateText error(서버 에러)");
        }
        return responseVo;
    }

    public ResponseVo getConversation(RequestQuestionVo requestQuestionVo) {
        ResponseVo responseVo = new ResponseVo();
        try {

            String answer = generateText(requestQuestionVo.getQuestion(), 0.5f, 1000);

            responseVo.setCode(200);
            String answerFilter1 = answer.replaceAll("\n", "");
            String result =  answerFilter1.replaceAll("\\.","");
            result =  result.replaceAll("\\\\","");
            result =  result.replaceAll("\"","");

            responseVo.setResponse(result);
        } catch (Exception e) {
            responseVo.setCode(500);
            responseVo.setResponse("generateText error(서버 에러)");
        }
        return responseVo;
    }


    public String generateText(String prompt, float temperature, int maxTokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model","text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("temperature", temperature);
        requestBody.put("max_tokens", maxTokens);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(COMPLETION_ENDPOINT, requestEntity, Map.class);
        Map<String, Object> responseBody = response.getBody();
        System.out.println(responseBody.toString());

        List<Map<String, Object>> choicesList = (List<Map<String, Object>>)responseBody.get("choices");
        Map<String, Object> choiceMap = choicesList.get(0);
        String answer = (String)choiceMap.get("text");

        return answer;
    }
}
