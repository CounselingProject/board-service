package xyz.sangdam.board.exceptions;

import xyz.sangdam.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class BoardDataNotFoundException extends CommonException {
    public BoardDataNotFoundException() {
        super("NotFound.boardData", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}