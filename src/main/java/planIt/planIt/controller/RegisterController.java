package planIt.planIt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.domain.User;
import planIt.planIt.service.UserService;

@RestController
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
//    public ResponseEntity<UserDTO> registerUser(User user){
      public String registerUser(User user){
        userService.save(user);
        return "123";
    }
}
