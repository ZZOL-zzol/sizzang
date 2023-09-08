package com.zzol.sizzang.user.service;

import com.zzol.sizzang.global.jwt.JwtTokenProvider;
import com.zzol.sizzang.global.jwt.TokenInfo;
import com.zzol.sizzang.user.dto.UserSignUpDto;
import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

//    private final JwtService jwtService;
//    /**
//     * User List 조회
//     */
//    public List<UserEntity> getUsers() {
//        return userRepository.findAll();
//    }
//
    /**
     * Id에 해당하는 User 조회
     * @param id
     */
    public UserEntity getUser(String id) {
        return userRepository.findByUserId(id).orElse(null);
    }
//
//    /**
//     * Id에 해당하는 User 삭제
//     * @param id
//     */
//    public void deleteUser(String id) {
//        userRepository.deleteById(id);
//    }
//

//    public UserEntity updateUserInfo(UserUpdateDTO updateData){
//        UserEntity updatedUser = null;
//
//        try {
//            if (updateData.isUserUpdateEmpty())
//                throw new Exception("Required info is not qualified");
//
//            UserEntity existUser = getUser(token.getUsername());
//
//            existUser.setNickname(updateData.getNickname());
//            existUser.setBirthDay(updateData.getBirthDay());
//            existUser.setPhoneNum(updateData.getPhoneNum());
//
//            if (!existUser.isUserInfoEmpty())
//                existUser.authorizeUser();
//
//            // if (!ObjectUtils.isEmpty(existUser))
//            // updatedUser = userRepository.save(model);
//
//        } catch (Exception e) {
//
//            log.info("[Fail] e: " + e.toString());
//        }
//
//        return updatedUser;
//    }


    /**
     * User 생성, 회원가입 메서드
     * JPA Repository의 save Method를 사용하여 객체를 생성
     */
//    public String signUp(UserSignUpDto userSignUpDto) throws Exception {
//        if (userRepository.findByUserId(userSignUpDto.getUserId()).isPresent()) { //아이디 중복 불가
//            throw new Exception("이미 존재하는 아이디입니다.");
//        }
//        if (userRepository.findByUserAccount(userSignUpDto.getUserAccount()).isPresent()){ //계좌 일치시
//            throw new Exception("이미 가입한 회원입니다.");
//        }
//
//        UserEntity user = UserEntity.builder()
//                .userName(userSignUpDto.getUserName())
//                .userId(userSignUpDto.getUserId())
//                .userAccount(userSignUpDto.getUserAccount())
//                .userNickname(userSignUpDto.getUserNickname())
//                .userPassword(userSignUpDto.getUserPassword())
//                .stamp(0)
//                .role(userSignUpDto.getRole())
//                .deleted(false)
//                .build();
//
//        userRepository.save(user);
//        return user.getUserNickname();
//    }
    public boolean signUp(UserSignUpDto userSignUpDto) throws Exception {
        if (userRepository.findByUserId(userSignUpDto.getUserId()).isPresent()) { //아이디 중복 불가
            return false;
        }
        if (userRepository.findByUserAccount(userSignUpDto.getUserAccount()).isPresent()){ //계좌 일치시
            return false;
        }

        UserEntity user = UserEntity.builder()
                .userName(userSignUpDto.getUserName())
                .userId(userSignUpDto.getUserId())
                .userAccount(userSignUpDto.getUserAccount())
                .userNickname(userSignUpDto.getUserNickname())
                .userPassword(userSignUpDto.getUserPassword())
                .stamp(0)
                .role(userSignUpDto.getRole())
                .deleted(false)
                .build();

        userRepository.save(user);
        return true;
    }


    @Transactional
    public TokenInfo login(String memberId, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        log.info("authenticationToken: {}", authenticationToken);
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }



}
