package planIt.planIt.controller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

// https://velog.io/@code-10/%EB%A1%AC%EB%B3%B5-AllNoArgsConstructor-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90

@Getter @Setter
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String pw;
    private String name;
    private String phone_number;
    private Date birth;

}
