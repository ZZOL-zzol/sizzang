package com.zzol.sizzang.user.controller;


import com.zzol.sizzang.user.entity.UserEntity;
import com.zzol.sizzang.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

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
}
