package planIt.planIt.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.common.auth.JwtTokenProvider;
import planIt.planIt.controller.dto.LoginDTO;
import planIt.planIt.service.UserService;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @PostMapping("/signIn")
    public String signIn (@Valid @RequestBody LoginDTO loginDTO) {

         return userService.login(loginDTO);

    }





}
