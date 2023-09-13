package com.zzol.sizzang.banking.repository;

import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, String> {

    Bank findByAccountNumber(String accountNumber);

    List<Bank> findByUserId(String userId);

//    Optional<Bank> findByUserCode(String userCode);

}


