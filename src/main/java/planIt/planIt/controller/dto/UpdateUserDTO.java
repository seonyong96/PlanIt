package planIt.planIt.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {

    @Size(max=20, message="비밀번호는 최대 15자 제한입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message="비밀번호는 영문+숫자 조합으로 입력해주세요.")
    private String pw;

    @Size(min=11, max=11, message="전화번호는 '-' 없이 11자리 숫자로 입력해주세요.")
    @Pattern(regexp = "^[0-9]+$", message="전화번호는 '-' 없이 11자리 숫자로 입력해주세요.")
    private String phoneNumber;

    @Email(message="E-Mail형식이 맞는지 확인해주세요.")
    private String email;


}
