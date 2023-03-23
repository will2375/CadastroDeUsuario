package com.UsuariosCadastro.cadastro.model.dto;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private String email;
}