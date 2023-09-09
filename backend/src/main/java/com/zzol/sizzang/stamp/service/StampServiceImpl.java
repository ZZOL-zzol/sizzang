package com.zzol.sizzang.stamp.service;

import com.zzol.sizzang.stamp.dto.request.StampReq;
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
    public List<StampEntity> getStampList(StampReq stampReq) {
        List<StampEntity> stampList = stampRepository.findAllByUserCode(stampReq.getUserCode());
        return stampList;
    }

    //스탬프 등록
    @Override
    public void addStamp(StampReq stampReq) {

        Optional<StampEntity> stamp = stampRepository.findByUserCodeAndRegionCode(stampReq.getUserCode(), stampReq.getRegionCode());

        if(stamp.isEmpty()) {
            StampEntity newStamp = new StampEntity();
            newStamp.setUserCode(stampReq.getUserCode());
            newStamp.setRegionCode(stampReq.getRegionCode());
            newStamp.setStampCount(1);
            stampRepository.save(newStamp);
        } else {
            StampEntity existingStamp = stamp.get();
            existingStamp.setStampCount(existingStamp.getStampCount() + 1);
            stampRepository.save(existingStamp);
        }
    }
}
