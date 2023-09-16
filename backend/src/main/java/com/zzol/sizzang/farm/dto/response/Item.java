package com.zzol.sizzang.farm.dto.response;

import lombok.*;

import java.util.List;
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String countyname;
    private Object itemname;
    private Object kindname;
    private String unit;
    private String price;
    private String weekprice;
    private String monthprice;
    private String yearprice;


}
