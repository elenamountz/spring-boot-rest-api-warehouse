package app.exception;

import app.common.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class FieldNotUniqueException extends BaseRuntimeException {

    public FieldNotUniqueException(String message) {
        super("Field " + message + " is not unique", HttpStatus.BAD_REQUEST);
    }
}
