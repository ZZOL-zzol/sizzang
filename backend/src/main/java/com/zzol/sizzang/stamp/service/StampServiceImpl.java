package com.zzol.sizzang.stamp.service;

import com.zzol.sizzang.stamp.dto.request.StampAddReq;
import com.zzol.sizzang.stamp.entity.StampEntity;
import com.zzol.sizzang.stamp.repository.StampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StampServiceImpl implements StampService{

    private StampRepository stampRepository;

    @Autowired
    public StampServiceImpl(StampRepository stampRepository){
        this.stampRepository = stampRepository;
    }

    //스탬프 등록
    @Override
    public void addStamp(StampAddReq stampAddReq) {

        Optional<StampEntity> stamp = stampRepository.findByUserCodeAndRegionCode(stampAddReq.getUserCode(), stampAddReq.getRegionCode());

        if(stamp.isEmpty()) {
            StampEntity newStamp = new StampEntity();
            newStamp.setUserCode(stampAddReq.getUserCode());
            newStamp.setRegionCode(stampAddReq.getRegionCode());
            newStamp.setStampCount(1);
            stampRepository.save(newStamp);
        } else {
            StampEntity existingStamp = stamp.get();
            existingStamp.setStampCount(existingStamp.getStampCount() + 1);
            stampRepository.save(existingStamp);
        }
    }
}
