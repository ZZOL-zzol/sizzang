package com.zzol.sizzang.banking.controller;


import com.zzol.sizzang.banking.dto.Request.Won1TransferRequestDto;
import com.zzol.sizzang.banking.dto.Response.SearchTransactionResponseDto;
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

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bank")
@RestController
@CrossOrigin
public class BankController {

    private final BankService bankService;
    private final UserService userService;

    @Operation(description = "1원이체 메서드")
    @PostMapping("/v1/auth/1transfer")
    public ResponseEntity<?> won1Transfer(@RequestBody Won1TransferRequestDto won1TransferRequestDto) {
        log.info("won1Transfer 요청");
        String checkMemo = bankService.won1Transfer(won1TransferRequestDto);
        return new ResponseEntity<>(checkMemo, HttpStatus.OK);
    }

//    @Operation(description = "거래 내역 확인 메서드")
//    @PostMapping("/v1/search/transaction")
//    public ResponseEntity<?> searchTransaction(@RequestBody String userId) {
//        log.info("searchTransaction 요청");
//        User user = userService.getUser(userId);
//        System.out.println(user.getUserAccount());
//        SearchTransactionResponseDto searchTransactionResponseDto = bankService.searchTransaction(user.getUserAccount());
//        return new ResponseEntity<>(searchTransactionResponseDto, HttpStatus.OK);
//    }







}
