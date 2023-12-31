package com.zzol.sizzang.banking.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Won1TransferResponseDto {
    private int bankCode;
    private String accountNumber;
    private String checkedKey; //본인계좌 확인을 위한 key
    private String successCode;
    private String resultCode;
    private String resultMessage;
}
