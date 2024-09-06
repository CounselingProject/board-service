package xyz.sangdam.board.exceptions;

import xyz.sangdam.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class GuestPasswordCheckException extends CommonException {
    public GuestPasswordCheckException() {
        super("RequiredCheck.guestPw", HttpStatus.UNAUTHORIZED);
        setErrorCode(true);
    }
}
