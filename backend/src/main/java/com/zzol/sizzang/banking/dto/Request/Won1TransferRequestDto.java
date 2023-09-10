package com.zzol.sizzang.banking.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Won1TransferRequestDto {
    private String bankCode;
    private String accountNumber;
}