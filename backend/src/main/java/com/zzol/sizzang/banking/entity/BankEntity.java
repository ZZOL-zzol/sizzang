package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
@Table(name = "bank")
public class BankEntity {
    @Id
    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "bank_name")
    private String bankName;
}
