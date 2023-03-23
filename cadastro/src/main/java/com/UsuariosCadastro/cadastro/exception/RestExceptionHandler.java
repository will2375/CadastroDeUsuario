package com.UsuariosCadastro.cadastro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequest(BadRequestException e) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request Exception, Check the Documentation")
                        .details(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ValidacaoDeDuplicidade.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerConflit(ValidacaoDeDuplicidade e) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request Exception, Check the Documentation")
                        .details(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .developerMessage(e.getClass().getName())
                        .build(), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoExceptionsDetaills> handlerArgument(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidacaoExceptionsDetaills.builder()
                        .title("Bad Request Exception, invalid fields")
                        .details("check field(s)")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(e.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build(), HttpStatus.BAD_REQUEST);

    }
}
