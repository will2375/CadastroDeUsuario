package com.UsuariosCadastro.cadastro.service.exceptions;

public class UsuarioNaoEncontrado extends RuntimeException{
    public UsuarioNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
