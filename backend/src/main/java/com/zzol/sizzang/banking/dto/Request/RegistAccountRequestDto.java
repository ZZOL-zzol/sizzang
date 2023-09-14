package com.zzol.sizzang.banking.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistAccountRequestDto {
    private String accountNumber;
    private String userId;
}
