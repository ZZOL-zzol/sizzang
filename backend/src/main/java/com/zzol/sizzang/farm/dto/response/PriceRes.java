package com.zzol.sizzang.farm.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Builder
@ToString
@Data
public class PriceRes {

    public List<String> condition;
    public String error_code;
    public List<Price> prices;

    @Data
    class Price {
            public String product_cls_code;
            public String product_cls_name;
            public String category_code;
            public String category_name;
            public String productno;
            public String lastest_day;
            public String productName;
            public String item_name;
            public String unit;
            public String day1;
            public String dpr1;
            public String day2;
            public String dpr2;
            public String day3;
            public String dpr3;
            public String day4;
            public String dpr4;
            public String direction;  //등락여부( 0:가격하락 1:가격상승 2:등락없음 )
            public String value;  //등락율
        }

}


