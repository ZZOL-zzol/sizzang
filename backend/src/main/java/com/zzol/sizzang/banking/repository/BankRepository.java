package com.zzol.sizzang.banking.repository;

import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, String> {
//    Optional<Bank> findByBankAccount(String userAccount);

//    Optional<Bank> findByUserCode(String userCode);

}

