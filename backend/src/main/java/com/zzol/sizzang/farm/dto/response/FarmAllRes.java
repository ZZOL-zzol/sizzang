package com.zzol.sizzang.farm.dto.response;

import lombok.*;

import java.util.List;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmAllRes {
    public List<List<String>> condition;
    public String error_code;
    public List<Price> price;
}
