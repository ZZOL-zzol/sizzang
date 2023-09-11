package com.zzol.sizzang.global.login;

import com.zzol.sizzang.global.jwt.JwtService;
import com.zzol.sizzang.user.repository.UserRepository;
import com.zzol.sizzang.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Value("${jwt.access.expiration}")
    private String accessTokenExpiration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {

        String userId = extractUsername(authentication); // 인증 정보에서 userId 추출
        String accessToken = jwtService.createAccessToken(userId); // JwtService의 createAccessToken을 사용하여 AccessToken 발급
        String refreshToken = jwtService.createRefreshToken(); // JwtService의 createRefreshToken을 사용하여 RefreshToken 발급

        // 응답 헤더에 AccessToken, RefreshToken 실어서 응답
        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);

        userRepository.findByUserId(userId)
                .ifPresent(user -> {
                    user.updateRefreshToken(refreshToken);
                    userRepository.saveAndFlush(user);
                });
        log.info("로그인에 성공하였습니다. userId : {}", userId);
        log.info("---------------------- AccessToken : {}", accessToken);
        log.info("발급된 AccessToken 만료 기간 : {}", accessTokenExpiration);
    }

    private String extractUsername(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //authentication : 사용자 인증정보, getPrincipal() : 현재 인증된 사용자의 주요(principal) 객체를 가져옴
        return userDetails.getUsername();
    }
}