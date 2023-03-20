package com.UsuariosCadastro.cadastro.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UsuarioRequest {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    @Email
    private String email;
    @CPF
    private String cpf;
}
