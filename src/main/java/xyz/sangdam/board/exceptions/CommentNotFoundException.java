package xyz.sangdam.board.exceptions;

import xyz.sangdam.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends CommonException {
    public CommentNotFoundException() {
        super("NotFound.comment", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}