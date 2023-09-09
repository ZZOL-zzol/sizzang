package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import java.util.Date;

@ToString
@Getter
@Entity
public class Bank {
    @Id
    @Column(name = "bank_code")
    private String bankCode;
    private String bankAccount; //계좌번호
    private String content; //거래내용
    private int accountBalance; //잔액
    private int withdrawalAmount; //출금금액
    private int depositAmount; //입금금액
    private int division; //구분 1: 입금, 2:출금
    private String userName;
}
