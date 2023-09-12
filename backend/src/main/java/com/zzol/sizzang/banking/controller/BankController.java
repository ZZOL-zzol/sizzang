package com.zzol.sizzang.banking.controller;


import com.zzol.sizzang.banking.dto.Request.*;
import com.zzol.sizzang.banking.dto.Response.BalanceDetailResponseDto;
import com.zzol.sizzang.banking.dto.Response.SearchTransactionResponseDto;
import com.zzol.sizzang.banking.dto.Response.TransferResponseDto;
import com.zzol.sizzang.banking.entity.Bank;
import com.zzol.sizzang.banking.service.BankService;
import com.zzol.sizzang.user.entity.User;
import com.zzol.sizzang.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bank")
@RestController
@CrossOrigin
public class BankController {

    private final BankService bankService;
    private final UserService userService;

    /**
     * 계좌인증용, 랜덤숫자 생성해서 db업데이트(이체), 응답값으로 랜덤숫자 보내주기
     */
    @Operation(description = "1원이체 메서드")
    @PostMapping("/v1/auth/1transfer")
    public ResponseEntity<?> won1Transfer(@RequestBody Won1TransferRequestDto won1TransferRequestDto) {
        log.info("won1Transfer 요청");
        String certificationKey = bankService.won1Transfer(won1TransferRequestDto);
        return new ResponseEntity<>(certificationKey, HttpStatus.OK);
    }

    @Operation(description = "거래 내역 확인 메서드")
    @PostMapping("/v1/search/transaction")
    public ResponseEntity<?> searchTransaction(@RequestBody SearchTransactionRequestDto searchTransactionRequestDto) {
        log.info("searchTransaction 요청");
        log.info("계좌확인 userId: {}", searchTransactionRequestDto.getUserAccount());

        SearchTransactionResponseDto searchTransactionResponseDto = bankService.searchTransaction(searchTransactionRequestDto.getUserAccount());
        return new ResponseEntity<>(searchTransactionResponseDto, HttpStatus.OK);
    }

    @Operation(description = "잔액 조회 메서드")
    @PostMapping("/v1/account/balance/detail")
    public ResponseEntity<?> checkBalanceDetail(@RequestBody BalanceDetailRequestDto balanceDetailRequestDto) {
        log.info("checkBalanceDetail 요청");
        log.info("계좌확인 : {}", balanceDetailRequestDto.getAccountNumber());
        BalanceDetailResponseDto responseDto = bankService.balanceDetail(balanceDetailRequestDto.getAccountNumber());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(description = "이체 메서드")
    @PostMapping("/v1/transfer/krw")
    public ResponseEntity<?> transfer(@RequestBody TransferRequestDto transferRequestDto) {
        log.info("transfer 요청");
        log.info("내 계좌확인 : {}", transferRequestDto.getMyAccountNumber());
        log.info("상대계좌 확인: {}", transferRequestDto.getOpponentAccountNumber());

        if (!bankService.transferPossible(transferRequestDto)) {
            return new ResponseEntity<>("잔액이 부족합니다", HttpStatus.METHOD_NOT_ALLOWED);
        }

        TransferResponseDto responseDto = bankService.transferMoney(transferRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(description = "계좌 등록 메서드")
    @PostMapping("/v1/auth/saveaccount")
    public ResponseEntity<?> registAccount(@RequestBody RegistAccountRequestDto registAccountRequestDto) {
        log.info("registAccount 요청");
        log.info("계좌확인 : {}", registAccountRequestDto.getAccountNumber());
        log.info("id : {}", registAccountRequestDto.getUserId());
        userService.updateUserAccount(registAccountRequestDto.getAccountNumber(), registAccountRequestDto.getUserId());
        return new ResponseEntity<>("계좌등록성공", HttpStatus.OK);
    }
}
