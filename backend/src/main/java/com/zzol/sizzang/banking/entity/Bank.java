package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "bank")
    private List<TransactionHistory> transactionHistoryList = new ArrayList<TransactionHistory>();

    private String accountNumber; //계좌번호
    private String userId; // -> userCode 수정
    private int accountBalance; //잔액
}
