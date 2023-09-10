package com.zzol.sizzang.banking.repository;

import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionHistory, String> {

    List<TransactionHistory> findByAccountNumber(String userAccount);

//    Optional<Bank> findByUserCode(String userCode);

}


