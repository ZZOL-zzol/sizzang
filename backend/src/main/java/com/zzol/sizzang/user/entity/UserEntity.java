package com.zzol.sizzang.user.entity;

import com.zzol.sizzang.user.common.Role;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User") // 테이블과 클래스명이 같을 경우 생략 가능
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;

    private String userName;
    private String userId;
    private String userAccount;
    private String userNickname;
    private String userPassword;
    private int stamp;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String refreshToken;
    private boolean deleted; //회원탈퇴 여부 구분

    // 비밀번호 암호화 메소드
    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
