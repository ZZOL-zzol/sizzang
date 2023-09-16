package com.zzol.sizzang.product.repository;

import com.zzol.sizzang.product.entity.PurchaseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Long> {
}
