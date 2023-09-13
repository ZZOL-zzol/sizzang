package com.zzol.sizzang.chatgpt.controller;

import com.zzol.sizzang.chatgpt.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/chatgpt")
@RestController
public class GptController {

    @Autowired
    GptService gptService;

    @PostMapping("/recommendMenu")
    public String recommendMenu(@RequestBody String ingredient){
        String text = gptService.recommendMenu(ingredient);
        return text;
    }

}
