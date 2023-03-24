package com.UsuariosCadastro.cadastro.service;

import com.UsuariosCadastro.cadastro.exception.BadRequestException;
import com.UsuariosCadastro.cadastro.exception.ValidacaoDeDuplicidade;
import com.UsuariosCadastro.cadastro.intregacaoExterna.client.BeerClient;
import com.UsuariosCadastro.cadastro.model.BeerModel;
import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.BeerRequest;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioResponse;
import com.UsuariosCadastro.cadastro.repository.BeerRepository;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
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
        return listaUsuarios.stream().map(model -> {
            return UsuarioResponse.builder()
                    .id(model.getId()).nome(model.getNome()).email(model.getEmail()).nascimento(model.getNascimento()).cerveja(model.getCerveja()).build();
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
        model.setCerveja(String.valueOf(client.getRandomBeer().stream().map(BeerRequest::getNome).collect(Collectors.toList())));

        return repository.save(model);
    }

    public UsuarioModel alterar(UsuarioModel model) {
        Optional<UsuarioModel> email = Optional.ofNullable(repository.findByEmail(model.getEmail()));
        Optional<UsuarioModel> cpf = Optional.ofNullable(repository.findByCpf(model.getCpf()));
        if (email.isPresent() && !email.get().getId().equals(model.getId())) {
            throw new ValidacaoDeDuplicidade("EMAIL ja cadastrado");
        }else if (cpf.isPresent() && !cpf.get().getId().equals(model.getId())) {
            throw new ValidacaoDeDuplicidade("CPF ja cadastrado");
        }
        return repository.save(model);
    }

    public UsuarioModel buscarPorID(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Usuario não encontrado"));
    }

    public void deletarUsuario(Long id) {
        buscarPorID(id);
        repository.deleteById(id);
    }
}

