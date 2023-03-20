package com.UsuariosCadastro.cadastro.service;


import com.UsuariosCadastro.cadastro.intregacaoExterna.client.BeerClient;
import com.UsuariosCadastro.cadastro.model.BeerModel;
import com.UsuariosCadastro.cadastro.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    @Autowired
    BeerClient client;

    @Autowired
    BeerRepository repository;
}
