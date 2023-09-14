package com.zzol.sizzang.chatgpt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatGptRequestDto {
    List<String> ingredients;
}
