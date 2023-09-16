package com.zzol.sizzang.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.zzol.sizzang.banking.repository.BankRepository;
import com.zzol.sizzang.global.jwt.JwtService;
import com.zzol.sizzang.review.repository.ReviewRepository;
import com.zzol.sizzang.s3.service.FileService;
import com.zzol.sizzang.s3.service.S3Service;
import com.zzol.sizzang.store.repository.StoreRepository;
import com.zzol.sizzang.user.dto.UploadUserimgRequestDto;
import com.zzol.sizzang.user.dto.UserSignUpDto;
import com.zzol.sizzang.user.entity.User;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BankRepository bankRepository;
    private final S3Service s3Service;



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
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    /**
     * User 생성, 회원가입 메서드
     * JPA Repository의 save Method를 사용하여 객체를 생성
     */
    public boolean signUp(UserSignUpDto userSignUpDto) throws Exception {
        if (userRepository.findByUserId(userSignUpDto.getUserId()).isPresent()) { //아이디 중복 불가
//            throw new Exception("이미 존재하는 아이디입니다.");
            return false;
//            return HttpStatus.
        }

        User user = User.builder()
                .userName(userSignUpDto.getUserName())
                .userId(userSignUpDto.getUserId())
//                .userAccount(userSignUpDto.getUserAccount())
                .userNickname(userSignUpDto.getUserNickname())
                .userPassword(userSignUpDto.getUserPassword())
                .stamp(0)
                .role(userSignUpDto.getRole())
                .deleted(false)
                .build();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return true;
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

    /**
     * 유저 계좌 등록 메서드
     * */
    @Transactional
    public void updateUserAccount(String userAccount, String userId){
        userRepository.registUserAccountByUserId(userAccount, userId);
        bankRepository.updateRegistUserAccountByUserId(userAccount);
    }


    /**
     * 이미지 선택 메서드
     * */
    public String selectImg(MultipartFile file) {
        String imgPath = s3Service.saveFile(file);
        return "https://d3brc3t3x7lzht.cloudfront.net/"+imgPath;
    }

    /**
     * 유저 이미지 등록 메서드
     * */
    @Transactional
    public void uploadUserImg(UploadUserimgRequestDto uploadUserimgRequestDto) {
        userRepository.registUserImgByUserId(uploadUserimgRequestDto.getUserImgUrl(), uploadUserimgRequestDto.getUserId());
    }

    /**
     * 유저 계좌 등록 메서드
     * */
    @Transactional
    public void deleteUserAccount(String userAccount){
        bankRepository.deleleRegistUserAccountByUserId(userAccount);
    }



}
