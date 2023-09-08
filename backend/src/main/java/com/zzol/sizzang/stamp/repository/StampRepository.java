package com.zzol.sizzang.stamp.repository;

import com.zzol.sizzang.stamp.entity.StampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StampRepository extends JpaRepository<StampEntity, Long> {

    Optional<StampEntity> findByUserCodeAndRegionCode(int userCode, int regionCode);

    List<StampEntity> findAllByUserCode(int userCode);

}
