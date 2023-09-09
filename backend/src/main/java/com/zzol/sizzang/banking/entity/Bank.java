package com.zzol.sizzang.banking.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Getter
@Entity
public class Bank {
    @Id
    @Column(name = "bank_code")
    private String bankCode;
}
