package com.zzol.sizzang.stamp.repository;

import com.zzol.sizzang.stamp.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    Optional<RegionEntity> findByRegionCode(int regionCode);
}
