package com.zzol.sizzang.user.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User") // 테이블과 클래스명이 같을 경우 생략 가능
@Entity
public class User {

    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCode;


    private String userName;
    @Column(name = "user_id")
    private String userId;
    private String userAccount;
    private String userNickname;
    private String userPassword;
    private int stamp;

//    @Enumerated(EnumType.STRING)
//    private Role role;
    private String role;
    private String refreshToken;
    private boolean deleted; //회원탈퇴 여부 구분
    private String userImg; //유저이미지url

    // 비밀번호 암호화 메소드
    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.userPassword = passwordEncoder.encode(this.userPassword);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    // UUID 생성 및 할당 메소드
//    public void generateUUID() {
//        this.UUID = UUID.randomUUID().toString();
//    }
}
