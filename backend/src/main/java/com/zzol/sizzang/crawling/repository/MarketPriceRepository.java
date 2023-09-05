package com.zzol.sizzang.crawling.repository;

import com.zzol.sizzang.crawling.entity.MarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface MarketPriceRepository extends JpaRepository<MarketPrice, Integer> {
}
