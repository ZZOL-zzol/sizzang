package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.entity.StoreEntity;

import java.util.List;
import java.util.Optional;

public interface QStoreRepository {

    List<StoreFindRes> findByCondition(FindByConditionGetReq findByConditionGetReq);

}
