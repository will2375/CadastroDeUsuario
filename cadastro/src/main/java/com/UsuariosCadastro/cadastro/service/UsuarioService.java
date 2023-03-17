package com.UsuariosCadastro.cadastro.service;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
import com.UsuariosCadastro.cadastro.service.exceptions.UsuarioNaoEncontrado;
import com.UsuariosCadastro.cadastro.service.exceptions.ValidacaoDeDuplicidade;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public List<UsuarioModel> modelList() {
        return repository.findAll();
    }

    public UsuarioModel cadastro(UsuarioModel model) {

        var existeCpf = repository.findByCpf(model.getCpf());
        var existeEmail = repository.findByEmail(model.getEmail());

        if (existeCpf != null) {
            throw new ValidacaoDeDuplicidade("CPF ja cadastrado");
        } else if (existeEmail != null) {
            throw new ValidacaoDeDuplicidade("EMAIL ja cadastrado");
        }
        return repository.save(model);
    }

    public Optional<UsuarioModel> buscarPorID (Long id) {
        var inexistente = repository.findById(id);
        if(inexistente == null) {
            throw new UsuarioNaoEncontrado("Usuario não existe ou não encontrado");
        }
       return repository.findById(id);
    }

    public void deletarUsuario(Long id) {
        buscarPorID(id);
        repository.deleteById(id);
    }
}

