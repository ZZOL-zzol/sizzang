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
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long bankCategoryCode;

    private String bankCode;
    private String bankName;

}
