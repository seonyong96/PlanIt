package planIt.planIt.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATE_ID(HttpStatus.CONFLICT, "이미 사용중인 ID 입니다."),
    MISSMATCH_PW(HttpStatus.BAD_REQUEST, "비밀번호와 새 비밀번호가 일치하지 않습니다."),
    NOTFOUND_ID(HttpStatus.BAD_REQUEST, "입력하신 정보와 일치하는 ID가 없습니다."),
    NOTFOUND_PW(HttpStatus.BAD_REQUEST, "입력하신 정보와 일치하는 비밀번호가 없습니다."),
    MISSMATCH_LOGIN(HttpStatus.BAD_REQUEST, "ID 혹은 PW 가 일치하지 않습니다."),
    NOTFOUND_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자 입니다."),
    DUPLICATE_ROLE(HttpStatus.BAD_REQUEST, "현재 ROLE과 동일합니다.");



    private final HttpStatus status;
    private final String message;

}
