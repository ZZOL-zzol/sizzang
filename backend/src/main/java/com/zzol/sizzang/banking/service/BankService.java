package com.zzol.sizzang.banking.service;

import com.zzol.sizzang.banking.dto.Request.BalanceDetailRequestDto;
import com.zzol.sizzang.banking.dto.Request.Won1TransferRequestDto;
import com.zzol.sizzang.banking.dto.Response.BalanceDetailResponseDto;
import com.zzol.sizzang.banking.dto.Response.SearchTransactionResponseDto;
import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import com.zzol.sizzang.banking.repository.BankRepository;
import com.zzol.sizzang.banking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BankService {

    private final TransactionRepository transactionRepository;
    private final BankRepository bankRepository;

    /**
     * 1원이체 메서드
     */
    public String won1Transfer(Won1TransferRequestDto won1TransferRequestDto) {
        String userAccount = won1TransferRequestDto.getAccountNumber();
        String userBankCode = won1TransferRequestDto.getBankCode();

        String certificationKey = makeRandomKey(); //랜덤 인증키 생성

        // 가장 최신 정보 불러오기
        TransactionHistory latestTransaction = transactionRepository.findLastestInfoByBankCode(userAccount);

        TransactionHistory userTransaction = new TransactionHistory();
        userTransaction.setAccountNumber(userAccount); //계좌
        userTransaction.setDepositAmount(1); //임금금액
        userTransaction.setDivision(1);//종류 : 입금
        userTransaction.setMyMsg(certificationKey+" ZZOL"); //인증키
        LocalDateTime currentDateTime = LocalDateTime.now();
        userTransaction.setTransactionDatetime(Timestamp.valueOf(currentDateTime));

        if (latestTransaction != null) {
            // 가장 최근의 거래 일자를 출력하거나 사용할 수 있음
            System.out.println("가장 최근 거래 일자: " + latestTransaction.getTransactionDatetime());
            System.out.println("현재잔액" + latestTransaction.getAccountBalance());
            userTransaction.setAccountBalance(latestTransaction.getAccountBalance()+1); //1원인증
            userTransaction.setBank(latestTransaction.getBank());
        } else {
            // 거래 내역이 없을 경우 처리
            System.out.println("거래 내역이 없습니다.");
            userTransaction.setAccountBalance(1); //1원인증
        }
        transactionRepository.save(userTransaction);
        log.info("certificationKey: {}", certificationKey);
        return certificationKey.toString();
    }

    /**
     * 거래내역 조회 메서드
     */
    public SearchTransactionResponseDto searchTransaction(String userAccount) {

        List<TransactionHistory> transactionHistory = transactionRepository.findByAccountNumber(userAccount);

        SearchTransactionResponseDto responseDto = new SearchTransactionResponseDto(transactionHistory);

        return responseDto;
    }

    /**
     * 잔액 조회 메서드
     */
    public BalanceDetailResponseDto balanceDetail(String accountNumber) {
        Bank bank = bankRepository.findByAccountNumber(accountNumber);
        log.info("bank.getAccountNumber():{}", bank.getAccountNumber());
        log.info("bank.getAccountBalance():{}", bank.getAccountBalance());
        BalanceDetailResponseDto balanceDetailResponseDto = new BalanceDetailResponseDto();
        balanceDetailResponseDto.setAccountNumber(accountNumber);
        balanceDetailResponseDto.setAccountBalance(bank.getAccountBalance());
        return balanceDetailResponseDto;
    }

    //랜덤 키 생성 메서드
    public String makeRandomKey(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder certificationKey = new StringBuilder(4);
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            certificationKey.append(characters.charAt(index));
        }
        return certificationKey.toString();
    }


}
