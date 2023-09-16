package com.zzol.sizzang.stamp.service;

import com.zzol.sizzang.stamp.dto.request.StampReq;
import com.zzol.sizzang.stamp.entity.RegionEntity;
import com.zzol.sizzang.stamp.entity.StampEntity;
import com.zzol.sizzang.stamp.repository.RegionRepository;
import com.zzol.sizzang.stamp.repository.StampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StampServiceImpl implements StampService{

    private StampRepository stampRepository;
    private RegionRepository regionRepository;

    @Autowired
    public StampServiceImpl(StampRepository stampRepository, RegionRepository regionRepository){
        this.stampRepository = stampRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public List<RegionEntity> getStampList(StampReq stampReq) {
        List<RegionEntity> stampList = regionRepository.getByUserCode(stampReq.getUserCode());

        return stampList;
    }

    //스탬프 등록
    @Override
    public String addStamp(StampReq stampReq) {

        Optional<StampEntity> stamp = stampRepository.findByUserCodeAndRegionCode(stampReq.getUserCode(), stampReq.getRegionCode());

        int regionCode;

        if(stamp.isEmpty()) {
            StampEntity newStamp = new StampEntity();
            newStamp.setUserCode(stampReq.getUserCode());
            newStamp.setRegionCode(stampReq.getRegionCode());
            newStamp.setStampCount(1);
            regionCode = stampRepository.save(newStamp).getRegionCode();
        } else {
            StampEntity existingStamp = stamp.get();
            existingStamp.setStampCount(existingStamp.getStampCount() + 1);
            regionCode = stampRepository.save(existingStamp).getRegionCode();
        }
        Optional<RegionEntity> region = regionRepository.findByRegionCode(regionCode);
        String stampedRegion = region.get().getRegionNameFirst() +" " + region.get().getRegionNameSecond();
        return stampedRegion;

    }
}
