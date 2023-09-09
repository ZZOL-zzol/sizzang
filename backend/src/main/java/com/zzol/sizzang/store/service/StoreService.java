package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.dto.response.StoreSelectRes;
import com.zzol.sizzang.store.entity.StoreEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {

    StoreEntity registStore(StoreRegistInsertReq insertInfo, MultipartFile file);

    List<StoreFindRes> selectAllStore();

    boolean modifyStore(StoreModifyPutReq modifyInfo, MultipartFile file);

    StoreSelectRes selectStore(Long stCode);

    List<StoreFindRes> findByCondition(FindByConditionGetReq findByConditionGetReq);

    Boolean deleteStore(Long stCode);
}
