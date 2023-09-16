package com.zzol.sizzang.farm.dto.response;

import lombok.*;
import net.bytebuddy.description.type.TypeList;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

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
        public Object dpr1;
        public String day2;
        public Object dpr2;
        public String day3;
        public Object dpr3;
        public String day4;
        public Object dpr4;
        public String direction;  //등락여부( 0:가격하락 1:가격상승 2:등락없음 )
        public String value;  //등락율
}
