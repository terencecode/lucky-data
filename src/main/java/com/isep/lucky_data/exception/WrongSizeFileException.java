package com.isep.lucky_data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WrongSizeFileException extends RuntimeException {

    public WrongSizeFileException() {}

    public WrongSizeFileException(String message) {
        super(message);
    }

    public WrongSizeFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
