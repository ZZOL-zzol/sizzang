package com.zzol.sizzang.farm.dto.response;

import lombok.*;

import java.util.List;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConditionDto {

    public String p_productclscode;
    public List<String> p_countycode;
    public String p_regday;
    public String p_itemcategorycode;
    public String p_itemcode;
    public String p_kindcode;
    public String p_productrankcode;
    public String p_convert_kg_yn;
    public String p_key;
    public String p_id;
    public String p_returntype;

}
