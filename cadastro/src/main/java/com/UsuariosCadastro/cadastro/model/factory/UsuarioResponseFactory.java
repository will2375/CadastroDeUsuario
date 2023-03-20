package com.UsuariosCadastro.cadastro.model.factory;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioResponse;

public class UsuarioResponseFactory {

    public static UsuarioResponse
    criar(UsuarioModel model) {
        return UsuarioResponse.builder()
                .id(model.getId())
                .nome(model.getNome())
                .nascimento(model.getNascimento())
                .email(model.getEmail()).build();
    }
}
