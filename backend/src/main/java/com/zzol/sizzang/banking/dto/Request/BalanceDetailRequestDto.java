package com.zzol.sizzang.banking.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BalanceDetailRequestDto {
    private String accountNumber; //계좌번호
}
