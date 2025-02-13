package planIt.planIt.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

DUPLICATE_ID(HttpStatus.CONFLICT, "이미 사용중인 ID 입니다.");

    private final HttpStatus status;
    private final String message;

}
