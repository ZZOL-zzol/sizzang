package com.zzol.sizzang.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    private String userId;
    private String userPassword;
}
