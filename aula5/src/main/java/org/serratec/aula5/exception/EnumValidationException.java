package org.serratec.aula5.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class EnumValidationException extends Exception {

    public EnumValidationException(String message) {
        super(message);
    }
}