package com.zzol.sizzang.farm.dto.response;

import lombok.*;

import java.util.List;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataDto {
    public String error_code;
    public List<Item> item;
}
