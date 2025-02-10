package planIt.planIt.controller;
import lombok.*;

import java.util.Date;

// https://velog.io/@code-10/%EB%A1%AC%EB%B3%B5-AllNoArgsConstructor-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String pw;
    private String name;
    private String phoneNumber;
    private String email;
    private String birth;

}
