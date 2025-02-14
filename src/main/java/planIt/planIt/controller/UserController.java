package planIt.planIt.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.controller.dto.UserDTO;
import planIt.planIt.controller.dto.UserIdSearchDTO;
import planIt.planIt.domain.User;
import planIt.planIt.service.UserService;

// 큰 범위 단위로 컨트롤러 짜기 ( UserController / PlanController / NotiController --알림 )

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }


    /** 회원가입
     * 
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> getUser(@Valid @RequestBody UserDTO dto){
        User user = userService.save(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /** ID찾기
     *
     * @param dto
     * @return ResponseEntity<>
     */
    @PostMapping("/userIdSearch")
    public ResponseEntity<User> getUserIdSearch(@Valid @RequestBody UserIdSearchDTO dto) {

        User user = userService.userIdSearch(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
