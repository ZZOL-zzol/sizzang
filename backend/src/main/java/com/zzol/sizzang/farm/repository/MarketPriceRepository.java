package com.zzol.sizzang.farm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.zzol.sizzang.farm.entity.MarketPriceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketPriceRepository extends JpaRepository<MarketPriceEntity, Long> {
    @Override
    void deleteAllInBatch();
}
