package com.UsuariosCadastro.cadastro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private String email;
    private String cerveja;
}