package com.UsuariosCadastro.cadastro.model.dto;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class UsuarioResponse {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private String email;
}