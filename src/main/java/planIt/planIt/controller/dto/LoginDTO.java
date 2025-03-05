package planIt.planIt.controller.dto;

import jakarta.validation.constraints.NotNull;
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
public class LoginDTO {

    @NotNull(message="ID는 필수항목 입니다.")
    @Size(max=15, message="ID는 최대 15자 제한입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message="ID는 영문+숫자 조합으로 입력해주세요.")
    private String userId;

    @NotNull(message="비밀번호는 필수항목 입니다.")
    @Size(max=20, message="비밀번호는 최대 15자 제한입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message="비밀번호는 영문+숫자 조합으로 입력해주세요.")
    private String pw;

}
