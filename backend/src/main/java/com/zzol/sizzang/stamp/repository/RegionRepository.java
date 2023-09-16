package com.zzol.sizzang.stamp.repository;

import com.zzol.sizzang.stamp.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    Optional<RegionEntity> findByRegionCode(int regionCode);

    @Query(value = "SELECT DISTINCT region.*" +
            "FROM (SELECT * FROM stamp WHERE user_code = ?1) AS st INNER JOIN region " +
            "ON st.region_code = region.region_code", nativeQuery = true)
    List<RegionEntity> getByUserCode(int userCode);
}
