package planIt.planIt.common.exeption;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice // 예외를 전역적으로 처리할 수 있는 어노테이션
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//        System.out.println("********ex test : " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return ResponseEntity.status(status)
                .body(ErrorResponse.builder()
                        .status(status.value())
                        .code(status.toString())
                        .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> CustomExceptionHandler(CustomException ex) {
//        System.out.println(ex.getErrorCode().getMessage() + "----------" + ex.getErrorCode().getStatus());

        return ResponseEntity.status(ex.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .status(ex.getErrorCode().getStatus().value())
                        .code(ex.getErrorCode().getStatus().toString())
                        .message(ex.getErrorCode().getMessage())
                        .build()
                );

    }

}
