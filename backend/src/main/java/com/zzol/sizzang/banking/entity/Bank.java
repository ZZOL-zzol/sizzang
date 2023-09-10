package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Entity
public class Bank {
    @Id
    @Column(name = "bank_code")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long bankCode;

    private String accountName;//계좌명

//    @OneToMany(mappedBy = "bank")
//    private List<TransactionHistory> transactionHistoryList;

    private String accountNumber; //계좌번호
    private String userId; //
    private int accountBalance; //잔액
}
