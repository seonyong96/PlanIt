package planIt.planIt.controller.dto.etc;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {

    @NotNull(message="E-Mail은 필수항목 입니다.")
    @Email(message="E-Mail형식이 맞는지 확인해주세요.")
    private String email;

    private int number;

    private int verifyNum;
}
