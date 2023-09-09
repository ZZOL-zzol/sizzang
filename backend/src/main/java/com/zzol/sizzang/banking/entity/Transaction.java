package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@ToString
@Getter
@Entity
public class Transaction {

    @Id
    @Column(name = "transaction_code")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long transactionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code") // 현재 엔터티(Transaction)의 외래 키 컬럼 이름
    private Bank bank;


    private String content; //거래내용
    private int accountBalance; //잔액
    private int withdrawalAmount; //출금금액
    private int depositAmount; //입금금액
    private int division; //구분 1: 입금, 2:출금
}
