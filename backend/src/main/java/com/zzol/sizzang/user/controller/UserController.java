package com.zzol.sizzang.user.controller;


import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.review.dto.request.ReviewAddReq;
import com.zzol.sizzang.user.dto.UploadUserimgRequestDto;
import com.zzol.sizzang.user.dto.UserSignUpDto;
import com.zzol.sizzang.user.entity.User;
import com.zzol.sizzang.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    /**
     * Member 생성
     *
     * @return
     * @throws ParseException
     */
    @Operation(description = "유저 등록 메서드입니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserSignUpDto userSignUpDto, HttpServletRequest request) throws Exception {
        Boolean signUpCheck = userService.signUp(userSignUpDto);
//        return ResponseEntity.ok(httpStatus);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(user);
        return new ResponseEntity<>(signUpCheck, HttpStatus.OK);
    }

    /**
     * User List 조회
     *
     * @return
     */
    @Operation(description = "유저 전체 조회 메서드입니다.")
    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    /**
     * Id에 해당하는 Member 조회
     *
     * @param userId
     * @return
     */
    @Operation(description = "특정 유저 조회 메서드입니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Id에 해당하는 Member 삭제
     * ✨
     *
     * @param id
     * @return
     */
    @Operation(description = "특정 유저 제거 메서드입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "testdd";
    }


    @Operation(description = "이미지 선택 메서드")
    @PostMapping("/selectImg")
    public CommonResponse<?> addImg(@RequestPart MultipartFile file) {
        String data = userService.selectImg(file);
        return CommonResponse.success(data);
    }

    @Operation(description = "유저 이미지 등록 메서드")
    @PutMapping("/registUserImg")
    public CommonResponse<?> registUserImg(@RequestBody UploadUserimgRequestDto uploadUserimgRequestDto) {
        userService.uploadUserImg(uploadUserimgRequestDto);
        return CommonResponse.success("이미지 등록 완료.");
    }


}
