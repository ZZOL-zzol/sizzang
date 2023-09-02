package com.zzol.sizzang.chatgpt.controller;

import com.zzol.sizzang.chatgpt.model.RequestQuestionVo;
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

    @RequestMapping(value = "/make/conversation", method = RequestMethod.GET)
    public ResponseVo makeConversation(@Valid RequestQuestionVo requestQuestionVo){
        ResponseVo responseVo = gptService.getConversation(requestQuestionVo);

        return responseVo;
    }

}
