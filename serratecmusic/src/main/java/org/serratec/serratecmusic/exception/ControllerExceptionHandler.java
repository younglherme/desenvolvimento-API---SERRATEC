package org.serratec.serratecmusic.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request){

        List<String> erros = new ArrayList<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erros.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status.value(),
                "Existe campos Inválidos , confira o preenchimento!", LocalDateTime.now(), erros);



        return this.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object>
    handleHttpMessageNotReadable(HttpMessageNotReadableException
                                         ex,
                                 HttpHeaders headers,
                                 HttpStatusCode status,
                                 WebRequest request) {
        return ResponseEntity.badRequest().body(ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request){
        ErroResposta erroResposta = new ErroResposta(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                List.of());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }

}

