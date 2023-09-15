package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.entity.StCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StCategoryRepository extends JpaRepository<StCategoryEntity, Integer> {

    Optional<StCategoryEntity> findByScCode(int scCode);
}
