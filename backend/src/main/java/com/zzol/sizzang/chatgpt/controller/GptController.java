package com.zzol.sizzang.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzol.sizzang.chatgpt.model.ChatGptRequestDto;
import com.zzol.sizzang.chatgpt.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chatgpt")
@RestController
public class GptController {

    @Autowired
    GptService gptService;

    //    @PostMapping("/recommendMenu")
//    public String recommendMenu(@RequestBody String ingredient) throws JsonProcessingException {
//        String text = gptService.recommendMenu(ingredient);
//        return text;
//    }
    @PostMapping("/recommendMenu")
    public List<String> recommendMenu(@RequestBody ChatGptRequestDto chatGptRequestDto) throws JsonProcessingException {

        List<String> ingredients = gptService.recommendMenu(chatGptRequestDto);
        return ingredients;
    }
}
