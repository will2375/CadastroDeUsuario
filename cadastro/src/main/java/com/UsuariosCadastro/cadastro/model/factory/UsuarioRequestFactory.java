package com.UsuariosCadastro.cadastro.model.factory;

import com.UsuariosCadastro.cadastro.model.BeerModel;
import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.BeerRequest;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioRequest;

public class UsuarioRequestFactory {

    public static UsuarioModel
    criar(UsuarioRequest request) {
        return UsuarioModel.builder()
                .id(request.getId())
                .nome(request.getNome())
                .cpf(request.getCpf())
                .nascimento(request.getNascimento())
                .email(request.getEmail()).build();
    }
}
