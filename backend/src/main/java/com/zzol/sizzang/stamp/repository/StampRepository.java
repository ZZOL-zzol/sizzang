package com.zzol.sizzang.stamp.repository;

import com.zzol.sizzang.stamp.entity.StampEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StampRepository extends JpaRepository<StampEntity, Long> {

    Optional<StampEntity> findByUserCodeAndRegionCode(int userCode, int regionCode);

}
