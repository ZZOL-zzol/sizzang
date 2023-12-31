package com.zzol.sizzang.banking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@ToString
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
    @Id
    @Column(name = "bank_code")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long bankCode;

    private String accountName;//계좌명

    @JsonIgnore
    @OneToMany(mappedBy = "bank")
    private List<TransactionHistory> transactionHistoryList;

    private String accountNumber; //계좌번호
    private String userId; // -> userCode 수정
    private int accountBalance; //잔액
    private boolean registed; //계좌 앱에 등록 여부, true:등록, false: 미등록

}
