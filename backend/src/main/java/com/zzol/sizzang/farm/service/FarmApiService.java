package com.zzol.sizzang.farm.service;

import com.zzol.sizzang.farm.dto.response.FarmAllRes;
import com.zzol.sizzang.farm.dto.response.FarmRegionalRes;
import com.zzol.sizzang.farm.dto.resquest.FarmApiReq;

public interface FarmApiService {

    FarmAllRes getRecentlyPrice(FarmApiReq farmApiReq);

    FarmRegionalRes getRegionalPrice(FarmApiReq farmApiReq);

}
