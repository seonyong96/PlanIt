package planIt.planIt.controller.dto.user;

import jakarta.validation.constraints.Email;
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
public class UserIdSearchDTO {

    private String userId;

    @NotNull(message="이름은 필수항목 입니다.")
    private String name;

    @NotNull(message="E-Mail은 필수항목 입니다.")
    @Email(message="E-Mail형식이 맞는지 확인해주세요.")
    private String email;

    @NotNull(message="생년월일은 필수항목 입니다.")
    @Size(min=6, max=6, message="생년월일은 yymmdd 으로 작성바랍니다. ex)960219")
    @Pattern(regexp = "^[0-9]+$", message="생년월일은 yymmdd 으로 작성바랍니다. ex)960219")
    private String birth;
}
