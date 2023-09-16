package com.zzol.sizzang.stamp.service;

import com.zzol.sizzang.stamp.dto.request.StampReq;
import com.zzol.sizzang.stamp.entity.RegionEntity;
import com.zzol.sizzang.stamp.entity.StampEntity;

import java.util.List;

public interface StampService {

    //스탬프 등록
    String addStamp(StampReq stampReq);
    List<RegionEntity> getStampList(StampReq stampReq);

}
