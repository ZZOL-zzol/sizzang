package com.zzol.sizzang.farm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.zzol.sizzang.farm.entity.MarketPriceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketPriceRepository extends JpaRepository<MarketPriceEntity, Long> {
    @Override
    void deleteAllInBatch();

    List<MarketPriceEntity> findByDirectionOrderByValueDesc(int direction);
}
