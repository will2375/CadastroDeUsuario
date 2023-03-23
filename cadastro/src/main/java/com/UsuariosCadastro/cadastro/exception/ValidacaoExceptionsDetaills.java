package com.UsuariosCadastro.cadastro.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidacaoExceptionsDetaills extends ExceptionsDetails{

    private final String fields;
    private final String fieldsMessage;

}
