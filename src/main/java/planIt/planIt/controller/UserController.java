package planIt.planIt.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.common.auth.JwtTokenProvider;
import planIt.planIt.controller.dto.*;
import planIt.planIt.domain.Email;
import planIt.planIt.domain.User;
import planIt.planIt.service.EmailService;
import planIt.planIt.service.UserService;

import java.util.HashMap;

// 큰 범위 단위로 컨트롤러 짜기 ( UserController / PlanController / NotiController --알림 )

@RestController
public class UserController {

    private final UserService userService;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, EmailService emailService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.emailService = emailService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 회원가입
     *
     * @param dto
     * @return ResponseEntity<>
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO dto) {
        User user = userService.save(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*** 이메일 인증 요청
     *
     * @param dto
     * @return ResponseEntity<>
     */
    @PostMapping("/mailSend")
    public ResponseEntity<Email> mailSend(@Valid @RequestBody EmailDTO dto) {
        Email email = emailService.sendMail(dto);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    /**
     * 이메일 인증번호 검증
     *
     * @param dto
     * @return boolean
     */
    @PostMapping("/mailNumberCheck")
    public boolean mailNumberCheck(@Valid @RequestBody EmailDTO dto) {
        return emailService.mailNumberCheck(dto);
    }

    /**
     * ID찾기
     *
     * @param dto
     * @return ResponseEntity<>
     */
    @PostMapping("/userIdSearch")
    public ResponseEntity<User> getUserIdSearch(@Valid @RequestBody UserIdSearchDTO dto) {

        User user = userService.userIdSearch(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    /**
     * PW 찾기
     * PW찾기 시 Vaild 검증
     *
     * @param dto
     * @return ResponseEntity
     */
    @PostMapping("/userPwSearch")
    public ResponseEntity<User> userPwSerach(@Valid @RequestBody UserPwSearchDTO dto) {

        User user = userService.userPwSearch(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    /**
     * PW찾기 시 새 비밀번호 DB저장
     *
     * @param dto
     * @return ResponseEntity
     */
    @PostMapping("/setNewPw")
    public ResponseEntity<User> setNewPw(@Valid @RequestBody UserPwSearchDTO dto) {

        User user = userService.setNewPw(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping("/roleChange")
    public ResponseEntity<User> roleChange(@Valid @RequestBody RoleChangeDTO dto) {

        User user = userService.roleChange(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
