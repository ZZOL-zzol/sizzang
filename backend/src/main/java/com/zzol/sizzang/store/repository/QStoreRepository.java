package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;

import java.util.List;

public interface QStoreRepository {

    List<StoreFindRes> findByCondition(FindByConditionGetReq findByConditionGetReq);
}
