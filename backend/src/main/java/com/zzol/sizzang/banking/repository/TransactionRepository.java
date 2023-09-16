package com.zzol.sizzang.banking.repository;

import com.zzol.sizzang.banking.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionHistory, String> {

    List<TransactionHistory> findByAccountNumber(String userAccount);

    @Query(value = " SELECT * " +
            " FROM transaction_history WHERE account_number = ?1 " +
            " ORDER BY transaction_datetime " +
            " DESC LIMIT 1 " , nativeQuery = true)
    TransactionHistory findLastestInfoByBankCode(String userAccount);
}


//    TransactionHistory findByAccountNumber(String userAccount);

//    Optional<Bank> findByAccountNumberAnd(String userCode);


