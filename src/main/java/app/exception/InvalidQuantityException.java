package app.exception;

import app.common.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class InvalidQuantityException extends BaseRuntimeException {

    public InvalidQuantityException(String message) {
        super("Not a positive quantity for " + message, HttpStatus.BAD_REQUEST);
    }
}
