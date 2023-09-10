package com.zzol.sizzang.product.repository;

import com.zzol.sizzang.product.entity.PrTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrTagRepository extends JpaRepository<PrTagEntity, Integer> {
}
