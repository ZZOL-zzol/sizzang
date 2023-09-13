package com.zzol.sizzang.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginResponseDto {
    private String userName;
    private String userId;
    private String userNickname;
    private String role;
    private String refreshToken;
    private boolean deleted; //회원탈퇴 여부 구분
}
