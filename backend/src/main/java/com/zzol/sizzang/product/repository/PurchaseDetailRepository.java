package com.zzol.sizzang.product.repository;

import com.zzol.sizzang.product.dto.request.PurchaseDetailDto;
import com.zzol.sizzang.product.entity.PurchaseDetailEntity;
import com.zzol.sizzang.product.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Long> {

    List<PurchaseDetailEntity> findAllByPuCode(Long puCode);
}
