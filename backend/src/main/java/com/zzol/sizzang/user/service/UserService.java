package com.zzol.sizzang.user.service;

import com.zzol.sizzang.jwt.JwtService;
import com.zzol.sizzang.user.dto.UserSignUpDto;
import com.zzol.sizzang.user.dto.UserUpdateDTO;
import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    /**
     * User List 조회
     */
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Id에 해당하는 User 조회
     * @param id
     */
    public UserEntity getUser(String id) {
        return userRepository.findByUserId(id).orElse(null);
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
     * User 생성
     * JPA Repository의 save Method를 사용하여 객체를 생성
     * Entity인 Model 객체에 @Id로 설정한 키 값이 없을 경우 해당하는 데이터를 추가
     * 만약 추가하려는 Entity인 Model 객체에 @Id 값이 이미 존재하면 갱신되기 때문에
     * 아래와 같이 추가하고자 하는 User가 존재하는지 체크하는 로직을 추가
     *
     *
     * @return
     */
    public String signUp(UserSignUpDto userSignUpDto) throws Exception {
        String id = jwtService.extractId(userSignUpDto.getAccessToken()).orElse(null);

        log.info("회원가입 : {}",id);

        if(id == null){
            throw new AuthenticationException("유효하지 않은 토큰 입니다.");
        }
        log.info("회원가입 시도 : {} {} {} {}",userSignUpDto.getUserId(), userSignUpDto.getUserName(), userSignUpDto.getUserAccount(), userSignUpDto.getUserNickname());
        //id, name, account, nickname 순서
        if (userRepository.findByUserAccount(id).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (userRepository.findByUserAccount(userSignUpDto.getUserAccount()).isPresent()){ //계좌 일치시
            throw new Exception("이미 가입한 회원입니다.");
        }
        UserEntity user = UserEntity.builder()
                .userName(userSignUpDto.getUserName())
                .userId(id)
                .userAccount(userSignUpDto.getUserAccount())
                .userNickname(userSignUpDto.getUserNickname())
                .userPassword(userSignUpDto.getUserPassword())
                .stamp(0)
                .role(userSignUpDto.getRole())
                .deleted(false)
                .build();

        userRepository.save(user);
        return user.getUserNickname();
    }



}
