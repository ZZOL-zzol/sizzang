package com.zzol.sizzang.user.service;

import com.zzol.sizzang.user.dto.UserUpdateDTO;
import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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



}
