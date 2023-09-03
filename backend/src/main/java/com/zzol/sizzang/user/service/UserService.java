package com.zzol.sizzang.user.service;

import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
     * JPA Repository의 findAll Method를 사용하여 전체 User를 조회
     *
     * @return
     */
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }



    /**
     * Id에 해당하는 User 조회
     * JPA Repository의 findBy Method를 사용하여 특정 User를 조회
     * find 메소드는 NULL 값일 수도 있으므로 Optional<T>를 반환하지만,
     * Optional 객체의 get() 메소드를 통해 Entity로 변환해서 반환함.
     *
     * @param id
     * @return
     */
    public UserEntity getUser(String id) {
        return userRepository.findByUserId(id).orElse(null);
    }

}
