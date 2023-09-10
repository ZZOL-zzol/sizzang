package com.zzol.sizzang.banking.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BalanceDetailResponseDto {
    private String accountNumber; //계좌번호
    private int accountBalance;//출금가능잔액
}
