package com.zzol.sizzang.product.repository;

import com.zzol.sizzang.product.entity.PdCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdCategoryRepository extends JpaRepository<PdCategoryEntity, Integer> {
}
