package com.zzol.sizzang.banking.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountListRequestDto {
    private String accountName; //계좌명
    private String accountNumber; //계좌번호
    private String userId;
}


