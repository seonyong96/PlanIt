package planIt.planIt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.domain.User;
import planIt.planIt.service.UserService;

@RestController
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<User> getUser(@RequestBody UserDTO dto){
        User user = userService.save(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
