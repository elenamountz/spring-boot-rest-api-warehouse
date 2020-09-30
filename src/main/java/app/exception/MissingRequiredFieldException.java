package app.exception;

import app.common.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class MissingRequiredFieldException extends BaseRuntimeException {

    public MissingRequiredFieldException(String message) {
        super("Field '" + message + "' is required", HttpStatus.BAD_REQUEST);
    }
}
