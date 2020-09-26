package app.exception;

import app.common.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class DuplicateCodeException extends BaseRuntimeException {

    public DuplicateCodeException(String message) {
        super("Code " + message + " is not unique", HttpStatus.NOT_ACCEPTABLE);
    }
}
