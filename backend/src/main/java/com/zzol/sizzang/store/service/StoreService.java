package com.zzol.sizzang.store.service;

import com.zzol.sizzang.store.dto.request.StoreModifyPutReq;
import com.zzol.sizzang.store.dto.request.StoreRegistInsertReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.dto.response.StoreSelectRes;
import com.zzol.sizzang.store.entity.StoreEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {

    StoreEntity insertStore(StoreRegistInsertReq insertInfo);

    List<StoreFindRes> selectAllStore();

    boolean modifyStore(StoreModifyPutReq modifyInfo, List<MultipartFile> files);

    StoreSelectRes selectStore(Long stCode);
}
