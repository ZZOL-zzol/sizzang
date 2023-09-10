package com.zzol.sizzang.banking.dto.Response;

import com.zzol.sizzang.banking.entity.TransactionHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SearchTransactionResponseDto {

    private List<TransactionHistory> transactionHistoryList;//거래 내역 리스트
}
