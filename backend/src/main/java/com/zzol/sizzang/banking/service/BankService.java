package com.zzol.sizzang.banking.service;

import com.zzol.sizzang.banking.dto.Request.Won1TransferRequestDto;
import com.zzol.sizzang.banking.dto.Response.SearchTransactionResponseDto;
import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import com.zzol.sizzang.banking.repository.BankRepository;
import com.zzol.sizzang.banking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class BankService {

    private final TransactionRepository transactionRepository;

    /**
     * 1원이체 메서드
     * */
    public String won1Transfer(Won1TransferRequestDto won1TransferRequestDto){
        // TODO: 거래 내역 업데이트 기능 추가
        String userAccount = won1TransferRequestDto.getAccount();
        String userBankCode = won1TransferRequestDto.getBankCode();
        String memo = won1TransferRequestDto.getMemo();
        memo = memo.replace("ZZOL",""); //서비스명 제거
        memo = memo.replace(" ",""); //공백제거
        log.info("won1Transfer key: {}", memo);
        return memo;
    }

    /**
     * 거래내역 조회 메서드
     * */
    public SearchTransactionResponseDto searchTransaction(String userAccount){

        List<TransactionHistory> transactionHistory = transactionRepository.findByAccountNumber(userAccount);

        SearchTransactionResponseDto responseDto = new SearchTransactionResponseDto(transactionHistory);

        return responseDto;
    }


}
