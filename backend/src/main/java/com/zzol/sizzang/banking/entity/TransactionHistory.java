package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

import java.sql.Date;

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

    private String myMsg; //거래내용 // 내계좌 (수정)
    private int accountBalance; //잔액
    private int withdrawalAmount; //출금금액
    private int depositAmount; //입금금액
    private int division; //구분 1: 입금, 2:출금
    private String transactionMsg; //상대계좌 내용
    private Date transactionDatetime; //거래일시
}
