package com.UsuariosCadastro.cadastro.model.factory;

import com.UsuariosCadastro.cadastro.model.BeerModel;
import com.UsuariosCadastro.cadastro.model.dto.BeerRequest;

import java.util.List;

public class BeerFactory {

public static BeerModel
    criar(BeerRequest request) {
    return BeerModel.builder()
            .id(request.getId())
            .nome(request.getNome())
            .porcentagemDeAlcool(request.getPorcentagemDeAlcool())
            .armagor(request.getArmagor()).build();
}
}
