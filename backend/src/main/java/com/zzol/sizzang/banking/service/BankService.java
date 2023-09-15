package com.zzol.sizzang.banking.service;

import com.zzol.sizzang.banking.dto.Request.*;
import com.zzol.sizzang.banking.dto.Response.BalanceDetailResponseDto;
import com.zzol.sizzang.banking.dto.Response.SearchAccountResponseDto;
import com.zzol.sizzang.banking.dto.Response.SearchTransactionResponseDto;
import com.zzol.sizzang.banking.dto.Response.TransferResponseDto;
import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.entity.TransactionHistory;
import com.zzol.sizzang.banking.repository.BankRepository;
import com.zzol.sizzang.banking.repository.TransactionRepository;

import java.util.ArrayList;

import com.zzol.sizzang.product.entity.ProductEntity;
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
        TransactionHistory latestTransaction = transactionRepository.findLastestInfoByBankCode(userAccount); //거래내역
        Bank bank = bankRepository.findByAccountNumber(userAccount); //계좌

        TransactionHistory userTransaction = new TransactionHistory();
        userTransaction.setAccountNumber(userAccount); //계좌
        userTransaction.setDepositAmount(1); //임금금액
        userTransaction.setDivision(1);//종류 : 입금
        userTransaction.setMyMsg(certificationKey + " ZZOL"); //인증키
        LocalDateTime currentDateTime = LocalDateTime.now();
        userTransaction.setTransactionDatetime(Timestamp.valueOf(currentDateTime));
        userTransaction.setBank(latestTransaction.getBank());

        if (latestTransaction != null) {
            // 가장 최근의 거래 일자를 출력하거나 사용할 수 있음
            System.out.println("가장 최근 거래 일자: " + latestTransaction.getTransactionDatetime());
            System.out.println("현재잔액" + latestTransaction.getAccountBalance());
            userTransaction.setAccountBalance(latestTransaction.getAccountBalance() + 1); //1원인증

            bank.setAccountBalance(latestTransaction.getAccountBalance() + 1); //계좌 잔액 업데이트

        } else {
            // 거래 내역이 없을 경우 처리
            System.out.println("거래 내역이 없습니다.");
            userTransaction.setAccountBalance(1); //1원인증
            bank.setAccountBalance(1); //계좌 잔액 업데이트
        }
        transactionRepository.save(userTransaction); //거래내역 db 업데이트

        log.info("certificationKey: {}", certificationKey);
        return certificationKey.toString();
    }

    /**
     * 거래내역 조회 메서드
     */
    public SearchTransactionResponseDto searchTransaction(String userAccount) {
        System.out.println(userAccount + "ㅇㅇㅇㅇㅇ");

        List<TransactionHistory> transactionHistory = transactionRepository.findByAccountNumber(userAccount);
        System.out.println(transactionHistory.get(0));

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
    public String makeRandomKey() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder certificationKey = new StringBuilder(4);
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            certificationKey.append(characters.charAt(index));
        }
        return certificationKey.toString();
    }

    //이체가능 여부 판단하는 메서드
    public boolean transferPossible(TransferRequestDto transferRequestDto) {
        Bank myBank = bankRepository.findByAccountNumber(transferRequestDto.getMyAccountNumber());
        if (myBank.getAccountBalance() >= transferRequestDto.getDepositAmount()) { //출금가능
            return true;
        }
        return false;
    }


    /**
     * 이체 구현 메서드
     */
    public TransferResponseDto transferMoney(TransferRequestDto transferRequestDto) {
        //1. 내계좌 거래내역 구현
        String myAccount = transferRequestDto.getMyAccountNumber(); //내계좌

        // 내 계좌 데이터 불러오기 -> 잔액 업데이트
        Bank myBank = bankRepository.findByAccountNumber(myAccount);
        int myAccountBalance = myBank.getAccountBalance(); //내 계좌 잔액
        int depositMoney = transferRequestDto.getDepositAmount(); //이체 희망 금액
        myBank.setAccountBalance(myAccountBalance - depositMoney);

        // 내 거래내역 데이터 불러오기 -> 거래내역 추가
        TransactionHistory newTransaction = new TransactionHistory();
        newTransaction.setAccountBalance(myAccountBalance - depositMoney);
        newTransaction.setAccountNumber(myAccount);
        newTransaction.setDepositAmount(0); //입금금액
        newTransaction.setDivision(2); //출금
        newTransaction.setMyMsg(transferRequestDto.getMyMsg()); //내계좌메모
        newTransaction.setTransactionDatetime(Timestamp.valueOf(LocalDateTime.now())); //거래일자
        newTransaction.setWithdrawalAmount(depositMoney); //출금금액


        //상대계좌 거래내역 구현
        //계좌 업데이트
        String opponentAccount = transferRequestDto.getOpponentAccountNumber();
        Bank opponentBank = bankRepository.findByAccountNumber(opponentAccount);
        opponentBank.setAccountBalance(opponentBank.getAccountBalance() + depositMoney);

        //거래내역 업데이트
        TransactionHistory opponentTransaction = new TransactionHistory();
        opponentTransaction.setAccountBalance(opponentBank.getAccountBalance() + depositMoney);
        opponentTransaction.setDepositAmount(depositMoney); //입금된 금액
        opponentTransaction.setDivision(1); //입금
        opponentTransaction.setTransactionDatetime(Timestamp.valueOf(LocalDateTime.now())); //거래일자
        opponentTransaction.setTransactionMsg(transferRequestDto.getTransactionMsg());

        transactionRepository.save(newTransaction);
        transactionRepository.save(opponentTransaction);

        TransferResponseDto responseDto = new TransferResponseDto(
                myAccount, //내계좌
                transferRequestDto.getMyMsg(), //내계좌표시(출금메모)
                transferRequestDto.getOppenentBankCode(), //상대계좌은행코드
                opponentAccount,//상대계좌
                transferRequestDto.getTransactionMsg(),//상대계좌표시
                depositMoney,
                myAccountBalance - depositMoney
        );
        return responseDto;
    }

    /**
     * 전체 계좌 검색 메서드
     */
    public List<SearchAccountResponseDto> searchAccountByUserId(SearchAllAccountRequestDto searchAllAccountRequestDto) {
        List<Bank> bankList = bankRepository.findByUserId(searchAllAccountRequestDto.getUserId());
        List<SearchAccountResponseDto> responseDtoList = new ArrayList<>();
        for (Bank bank : bankList) {
            SearchAccountResponseDto responseDto = new SearchAccountResponseDto();
            List<Bank> accountList = new ArrayList<>();
            accountList.add(bank);
            responseDto.setAccountList(accountList);
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    /**
     * 이미 등록된 계좌 검색 메서드
     */
    public List<SearchAccountResponseDto> searchRegistedAccounts(
            SearchRegistedAccountRequestDto requestDto) {
        List<Bank> bankList = bankRepository.findRegistedAccountsByUserId(requestDto.getUserId());
        List<SearchAccountResponseDto> responseDtoList = new ArrayList<>();
        for (Bank bank : bankList) {
            SearchAccountResponseDto responseDto = new SearchAccountResponseDto();
            List<Bank> accountList = new ArrayList<>();
            accountList.add(bank);
            responseDto.setAccountList(accountList);
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }


    /**
     * 내계좌 목록 리스트 추가 메서드
     */
    public Bank addUserAccountList(AddAccountListRequestDto requestDto) {
        Bank registNewBank = Bank.builder()
                .accountBalance(1000000)
                .bankCode(0)
                .accountName(requestDto.getAccountName())
                .accountNumber(requestDto.getAccountNumber())
                .registed(false)
                .userId(requestDto.getUserId())
                .build();
        bankRepository.save(registNewBank);
        return registNewBank;
    }

}