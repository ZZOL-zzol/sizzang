package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>, QStoreRepository {

    List<StoreEntity> findByMarketEntity_MkCode(int mkCode);

    Optional<StoreEntity> findByStCode(Long stCode);

    @Query(value = "SELECT AVG(st.stScore) " +
            "FROM StoreEntity st " +
            "WHERE st.marketEntity.mkCode = :mkCode")
    double getReviewScore(@Param("mkCode") int mkCode);

    List<StoreEntity> findByUser_UserCode(Long userCode);

}
