package planIt.planIt.common.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import planIt.planIt.common.enums.ErrorCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
}
