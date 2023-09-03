package com.zzol.sizzang.store.repository;

import com.zzol.sizzang.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepositoty extends JpaRepository<StoreEntity, String> {


}
