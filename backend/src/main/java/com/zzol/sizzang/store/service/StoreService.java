package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.entity.StoreEntity;

public interface StoreService {

    StoreEntity insertStore(StoreRegistInsertReq insertInfo);
}
