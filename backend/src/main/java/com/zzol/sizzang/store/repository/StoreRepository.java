package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>, QStoreRepository {

    List<StoreFindRes> findByMarketEntity_MkCode(int mkCode);
}
