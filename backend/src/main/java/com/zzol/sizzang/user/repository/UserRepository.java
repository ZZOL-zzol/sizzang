package com.zzol.sizzang.user.repository;

import com.zzol.sizzang.banking.entity.TransactionHistory;
import com.zzol.sizzang.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByUserAccount(String userAccount);

    @Modifying
    @Query(value = " UPDATE user " +
            " SET user_account = ?1 " +
            " WHERE user_id = ?2 " , nativeQuery = true)
    void registUserAccountByUserId(String userAccount, String userId);

}

