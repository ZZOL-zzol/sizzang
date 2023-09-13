package com.zzol.sizzang.chatgpt.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestRecommFoodVo {
//    @NotNull
//    @NotEmpty
//    @Size(max = 100)
    private String text;
}
