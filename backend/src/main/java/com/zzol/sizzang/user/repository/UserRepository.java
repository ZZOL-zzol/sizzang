package com.zzol.sizzang.user.repository;

import com.zzol.sizzang.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByRefreshToken(String refreshToken);

    Optional<UserEntity> findByUserAccount(String userAccount);



}
