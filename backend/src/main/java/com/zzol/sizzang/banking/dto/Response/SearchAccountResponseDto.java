package com.zzol.sizzang.banking.dto.Response;

import com.zzol.sizzang.banking.entity.Bank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SearchAccountResponseDto {
    private List<Bank> accountList;
}
