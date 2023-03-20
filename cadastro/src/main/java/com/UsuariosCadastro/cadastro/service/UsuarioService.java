package com.UsuariosCadastro.cadastro.service;

import com.UsuariosCadastro.cadastro.intregacaoExterna.client.BeerClient;
import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioResponse;
import com.UsuariosCadastro.cadastro.repository.BeerRepository;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
import com.UsuariosCadastro.cadastro.service.exceptions.UsuarioNaoEncontrado;
import com.UsuariosCadastro.cadastro.service.exceptions.ValidacaoDeDuplicidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerClient client;


    public List<UsuarioResponse> modelList() {
        List<UsuarioModel> listaUsuarios = repository.findAll();
        return listaUsuarios.stream().map(model ->{
            return UsuarioResponse.builder()
                    .id(model.getId()).nome(model.getNome()).email(model.getEmail()).nascimento(model.getNascimento()).build();
        }).collect(Collectors.toList());
    }

    public UsuarioModel cadastro(UsuarioModel model) {

        var existeCpf = repository.findByCpf(model.getCpf());
        var existeEmail = repository.findByEmail(model.getEmail());

        if (existeCpf != null) {
            throw new ValidacaoDeDuplicidade("CPF ja cadastrado");
        } else if (existeEmail != null) {
            throw new ValidacaoDeDuplicidade("EMAIL ja cadastrado");
        }
//        BeerModel beerModel = (BeerModel) client.getRandomBeer();
//        beerModel.getAddBeer().setId(model.getId());
//
//        beerRepository.save(beerModel);

        return repository.save(model);

    }

    public Optional<UsuarioModel> buscarPorID(Long id) {
        var inexistente = repository.findById(id);
        if (inexistente == null) {
            throw new UsuarioNaoEncontrado("Usuario não existe ou não encontrado");
        }
        return repository.findById(id);
    }

    public void deletarUsuario(Long id) {
        buscarPorID(id);
        repository.deleteById(id);
    }
}

