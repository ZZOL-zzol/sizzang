package com.zzol.sizzang.farm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.zzol.sizzang.farm.entity.MarketPriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketPriceRepository extends JpaRepository<MarketPriceEntity, Long> {
    @Override
    void deleteAllInBatch();

    @Query(value = "SELECT DISTINCT product_name FROM marketprice WHERE direction=?1 ORDER BY `value` DESC LIMIT 3", nativeQuery = true)
    List<String> getCheaperItems(int direction);

    @Query(value = "SELECT * FROM marketprice WHERE product_name=?1 ORDER BY `value` DESC LIMIT 1", nativeQuery = true)
    MarketPriceEntity getCheaperItemEntity(String item);

}
