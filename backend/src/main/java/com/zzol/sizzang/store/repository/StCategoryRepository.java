package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.entity.StCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StCategoryRepository extends JpaRepository<StCategoryEntity, Integer> {
}
