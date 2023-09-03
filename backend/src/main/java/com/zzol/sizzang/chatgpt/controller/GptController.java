package com.zzol.sizzang.chatgpt.controller;

import com.zzol.sizzang.chatgpt.model.RequestQuestionVo;
import com.zzol.sizzang.chatgpt.model.RequestRecommFoodVo;
import com.zzol.sizzang.chatgpt.model.ResponseVo;
import com.zzol.sizzang.chatgpt.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/chatgpt")
@RestController
public class GptController {

    @Autowired
    GptService gptService;

    @RequestMapping(value = "/make/food", method = RequestMethod.GET)
    public ResponseVo recommFood(@RequestBody RequestRecommFoodVo requestRecommFoodVo){
        System.out.println(requestRecommFoodVo.getText());
        ResponseVo responseVo = gptService.getRecommendFood(requestRecommFoodVo);

        return responseVo;
    }

    @RequestMapping(value = "/make/conversation", method = RequestMethod.GET)
    public ResponseVo makeConversation(@Valid RequestQuestionVo requestQuestionVo){
        ResponseVo responseVo = gptService.getConversation(requestQuestionVo);
        System.out.println(requestQuestionVo.getQuestion());
        return responseVo;
    }

}
