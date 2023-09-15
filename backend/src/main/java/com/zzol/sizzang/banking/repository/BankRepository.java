package com.zzol.sizzang.banking.repository;

import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByAccountNumber(String accountNumber);

    List<Bank> findByUserId(String userId);

    @Query(value = " SELECT * " +
        " FROM bank WHERE registed = TRUE "
        + " AND user_id =?1" , nativeQuery = true)
    List<Bank> findRegistedAccountsByUserId(String userId);

    @Modifying
    @Query(value = " UPDATE bank " +
            " SET registed = TRUE  " +
            " WHERE account_number = ?1 " , nativeQuery = true)
    void updateRegistUserAccountByUserId(String userAccount);
}


