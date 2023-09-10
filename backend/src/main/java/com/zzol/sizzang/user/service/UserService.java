package com.zzol.sizzang.user.service;

import com.zzol.sizzang.global.jwt.JwtService;
import com.zzol.sizzang.user.dto.UserSignUpDto;
import com.zzol.sizzang.user.entity.User;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    /**
     * User List 조회
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Id에 해당하는 User 조회
     * @param id
     */
    public User getUser(String id) {
        return userRepository.findByUserId(id).orElse(null);
    }

    public User findByUserAccount(String userAccount) {
        return userRepository.findByUserAccount(userAccount).orElse(null);
    }

    /**
     * Id에 해당하는 User 삭제
     * @param id
     */
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


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
    public User signUp(UserSignUpDto userSignUpDto) throws Exception {
        if (userRepository.findByUserId(userSignUpDto.getUserId()).isPresent()) { //아이디 중복 불가
            throw new Exception("이미 존재하는 아이디입니다.");
//            return HttpStatus.
        }
        if (userRepository.findByUserAccount(userSignUpDto.getUserAccount()).isPresent()){ //계좌 일치시
            throw new Exception("이미 가입한 회원입니다.");
        }
        User user = User.builder()
                .userName(userSignUpDto.getUserName())
                .userId(userSignUpDto.getUserId())
                .userAccount(userSignUpDto.getUserAccount())
                .userNickname(userSignUpDto.getUserNickname())
                .userPassword(userSignUpDto.getUserPassword())
                .stamp(0)
                .role(userSignUpDto.getRole())
                .deleted(false)
                .build();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return user;
    }

    public void loginAndUpdateRefreshToken(String userId, String refreshToken){
//        userRepository.findByUserId(userId)
//                .ifPresent(user -> {
//                    user.updateRefreshToken(refreshToken);
//                    userRepository.saveAndFlush(user); //Spring Data JPA에서 사용되는 메서드, 변경데이터 db 저장됨
//                });
        userRepository.findByUserId(userId)
                .ifPresentOrElse(
                        user -> {
                            user.updateRefreshToken(refreshToken);
                            userRepository.saveAndFlush(user);
                        },
                        () -> {
                            throw new RuntimeException("사용자를 찾을 수 없습니다.");
                        }
                );

    }

}
