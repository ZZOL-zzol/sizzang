package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
@Table(name = "bank_category")
public class BankCategory {
    @Id
    @Column(name = "bc_code")
    private String bankCode;
    @Column(name = "bank_name")
    private String bankName;
}
