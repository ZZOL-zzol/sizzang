package com.zzol.sizzang.product.repository;

import com.zzol.sizzang.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByStoreEntity_StCode(Long stCode);
}
