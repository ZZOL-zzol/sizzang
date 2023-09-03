package com.zzol.sizzang.user.service;

import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
