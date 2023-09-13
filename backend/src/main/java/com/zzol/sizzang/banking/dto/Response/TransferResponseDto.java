package com.zzol.sizzang.banking.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransferResponseDto {
    private String myAccountNumber; //내 계좌
    private String myMsg; //내 계좌 표시(출금계좌통장메모)
    private String oppenentBankCode; //상대계좌은행코드
    private String opponentAccountNumber; //상대계좌
    private String transactionMsg; //상대계좌 표시(메모)
    private int depositAmount;// 입금금액
    private int afterTransferAmount; //거래후 잔액
}
