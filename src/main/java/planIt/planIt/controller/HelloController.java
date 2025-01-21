package planIt.planIt.controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @GetMapping("/test")
    public String hello() {
        return "테스트입니다.";
    }


}