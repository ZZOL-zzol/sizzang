package com.zzol.sizzang.user.dto;

import com.zzol.sizzang.user.common.Role;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserSignUpDto {
    private String userName;
    private String userId;
//    private String userAccount;
    private String userNickname;
    private String userPassword;
    private String role;
}
