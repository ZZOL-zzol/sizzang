package com.zzol.sizzang.farm.dto.response;

import lombok.*;

import java.util.List;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmRegionalRes {
    public List<ConditionDto> condition;
    public DataDto data;
}
