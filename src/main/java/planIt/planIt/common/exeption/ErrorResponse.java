package planIt.planIt.common.exeption;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private final int status;     // http status   status
    private final String code;    // error code    status.value
    private final String message; // error message ex.message

}
