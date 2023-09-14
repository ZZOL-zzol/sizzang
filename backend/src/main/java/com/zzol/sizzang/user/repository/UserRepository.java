package com.zzol.sizzang.user.repository;

import com.zzol.sizzang.user.entity.User;
import kotlin.OptIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByUserAccount(String userAccount);

    Optional<User> findByUserCode(long userCode);

    @Modifying
    @Query(value = " UPDATE user " +
            " SET user_account = ?1 " +
            " WHERE user_id = ?2 " , nativeQuery = true)
    void registUserAccountByUserId(String userAccount, String userId);


    @Modifying
    @Query(value = " UPDATE user " +
            " SET user_img = ?1 " +
            " WHERE user_id = ?2 " , nativeQuery = true)
    void registUserImgByUserId(String userImgUrl, String userId);

}

