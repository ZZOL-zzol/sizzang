package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.entity.StoreEntity;

import java.util.List;

public interface StoreService {

    StoreEntity insertStore(StoreRegistInsertReq insertInfo);

    List<StoreFindRes> selectAllStore();
}
