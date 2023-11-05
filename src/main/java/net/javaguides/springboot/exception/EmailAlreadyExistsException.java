package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class EmailAlreadyExistsException extends RuntimeException {
    private String message;

    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
