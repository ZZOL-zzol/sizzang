package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Entity
public class Bank {
    @Id
    @Column(name = "bank_code")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long bankCode;

    private String bankAccount; //계좌번호
    private String userCode;

    @OneToMany(mappedBy = "bank")
    private List<Transaction> transactionList;
}
