package com.zzol.sizzang.chatgpt.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatCompletion {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Map<String, Integer> usage;

    @Getter
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;
    }

    @Getter
    public static class Message {
        private String role;
        private String content;
    }
}