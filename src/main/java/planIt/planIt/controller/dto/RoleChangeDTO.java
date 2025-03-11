package planIt.planIt.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleChangeDTO {

    @NotNull(message = "userId 는 필수항목 입니다.")
    private String userId;
    @NotNull(message = "newRole 은 필수항목 입니다.")
    private String newRole;

}
