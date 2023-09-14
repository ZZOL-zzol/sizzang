package com.zzol.sizzang.market.dto.request;

import com.zzol.sizzang.common.model.CommonResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;


@Data
public class MarketGetReq {

    private String mkName;
    private int limit;
    private int offset;
}
