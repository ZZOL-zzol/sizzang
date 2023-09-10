package com.zzol.sizzang.global.login;

import com.zzol.sizzang.user.dto.UserLoginResponseDto;
import com.zzol.sizzang.user.entity.User;
import com.zzol.sizzang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        UserDetails userDetails= org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPassword())
//                .authorities("user")
                .roles(user.getRole())
                .build();
        return userDetails;

    }
}
