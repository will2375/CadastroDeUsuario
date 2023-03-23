package com.UsuariosCadastro.cadastro.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ValidacaoDeDuplicidade extends RuntimeException{
    public ValidacaoDeDuplicidade(String message) {
        super(message);
    }
}
