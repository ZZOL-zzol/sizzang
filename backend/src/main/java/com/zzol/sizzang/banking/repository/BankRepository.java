package com.zzol.sizzang.banking.repository;

import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<User, String> {
    Optional<Bank> findByUserAccount(String userAccount);
}
