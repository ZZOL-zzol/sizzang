package com.zzol.sizzang.banking.service;

import com.zzol.sizzang.banking.dto.Request.Won1TransferRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class BankService {




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
}
