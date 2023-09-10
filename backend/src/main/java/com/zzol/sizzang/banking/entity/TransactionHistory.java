package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@ToString
@Getter
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @Column(name = "th_code")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long transactionCode;


    @JoinColumn(name = "account_number") // 현재 엔터티(Transaction)의 외래 키 컬럼 이름
    private String accountNumber; //계좌

    private String content; //거래내용
    private int accountBalance; //잔액
    private int withdrawalAmount; //출금금액
    private int depositAmount; //입금금액
    private int division; //구분 1: 입금, 2:출금
}
