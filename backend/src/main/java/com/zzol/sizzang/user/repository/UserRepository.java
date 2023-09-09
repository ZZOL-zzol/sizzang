package com.zzol.sizzang.user.repository;

import com.zzol.sizzang.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByUserAccount(String userAccount);



}
