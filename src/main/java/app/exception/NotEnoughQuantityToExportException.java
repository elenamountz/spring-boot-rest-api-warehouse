package app.exception;

import app.common.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;

public class NotEnoughQuantityToExportException extends BaseRuntimeException {

    public NotEnoughQuantityToExportException(String message) {
        super("Not enough quantity for " + message, HttpStatus.BAD_REQUEST);
    }
}