package planIt.planIt.controller;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

// https://velog.io/@code-10/%EB%A1%AC%EB%B3%B5-AllNoArgsConstructor-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message="ID는 필수항목 입니다.")
    @Size(max=15, message="ID는 최대 15자 제한입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message="ID는 영문+숫자 조합으로 입력해주세요.")
    private String userId;

    @NotNull(message="비밀번호는 필수항목 입니다.")
    @Size(max=20, message="비밀번호는 최대 15자 제한입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message="비밀번호는 영문+숫자 조합으로 입력해주세요.")
    private String pw;

    @NotNull(message="이름은 필수항목 입니다.")
    private String name;

    @NotNull(message="전화번호는 필수항목 입니다.")
    @Size(min=11, max=11, message="전화번호는 '-' 없이 11자리 숫자로 입력해주세요.")
    @Pattern(regexp = "^[0-9]+$", message="전화번호는 '-' 없이 11자리 숫자로 입력해주세요.")
    private String phoneNumber;

    @NotNull(message="E-Mail은 필수항목 입니다.")
    @Email(message="E-Mail형식이 맞는지 확인해주세요.")
    private String email;

    @NotNull(message="생년월일은 필수항목 입니다.")
    @Size(min=6, max=6, message="생년월일은 yymmdd 으로 작성바랍니다. ex)960219")
    @Pattern(regexp = "^[0-9]+$", message="생년월일은 yymmdd 으로 작성바랍니다. ex)960219")
    private String birth;

}
