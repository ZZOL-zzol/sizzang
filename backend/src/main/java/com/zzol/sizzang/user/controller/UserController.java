package com.zzol.sizzang.user.controller;


import com.zzol.sizzang.jwt.JwtService;
import com.zzol.sizzang.user.dto.UserLoginDto;
import com.zzol.sizzang.user.dto.UserSignUpDto;
import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    /**
     * Member 생성
     *
     * @return
     * @throws ParseException
     */
    @Operation(description = "유저 등록 메서드입니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserSignUpDto userSignUpDto, HttpServletRequest request) throws Exception {
        String userName = userService.signUp(userSignUpDto);
        return ResponseEntity.ok(userName);
    }

    /**
     * User List 조회
     *
     * @return
     */
    @Operation(description = "유저 전체 조회 메서드입니다.")
    @GetMapping()
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    /**
     * Id에 해당하는 Member 조회
     *
     * @param id
     * @return
     */
    @Operation(description = "특정 유저 조회 메서드입니다.")
    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") String id) {
        UserEntity user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Id에 해당하는 Member 삭제
     *✨
     * @param id
     * @return
     */
    @Operation(description = "특정 유저 제거 메서드입니다.")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(description = "유저 로그인 메서드입니다.")
    @GetMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws Exception {
        String userId = userLoginDto.getUserId();
        log.info("로그인 시도 id: {}", userId);
        String userPassword = userLoginDto.getUserPassword();
        if(userService.getUser(userId) == null){
            return new ResponseEntity<>("해당하는 아이디가 없습니다.", HttpStatus.NOT_FOUND);
        }
        UserEntity user = userService.getUser(userId);
        if(user.getUserPassword() != userLoginDto.getUserPassword()){
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.NOT_FOUND);
        }
        //아이디와 비밀번호 일치시 토큰 발급
        String accessToken = jwtService.createAccessToken(userId);
        String refreshToken = jwtService.createRefreshToken();
        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken); //응답 헤더에 accessToken 과 refreshToken 을 담아서 전송

        log.info("로그인에 성공하였습니다. userId : {}", userId);
        log.info("---------------------- AccessToken : {}", accessToken);

        userService.loginAndUpdateRefreshToken(userId, refreshToken); //refreshToken update

        return ResponseEntity.ok("로그인성공");
    }


//  @Operation(description = "유저 정보 수정 메서드입니다.")
//  @PutMapping()
//  public ResponseEntity<UserEntity> updateUser(@RequestBody UserUpdateDTO updateData) throws ParseException {
//
//    UserEntity updatedUser = userService.updateUserInfo(updateData); //TODO: token 기능 추가후 수정 예정
//
//    if (!ObjectUtils.isEmpty(updatedUser)) {
//
//      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//
//    } else {
//
//      return new ResponseEntity<>(updatedUser, HttpStatus.NOT_FOUND);
//    }
//  }

}
