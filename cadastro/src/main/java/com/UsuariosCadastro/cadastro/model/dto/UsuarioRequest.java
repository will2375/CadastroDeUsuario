package com.UsuariosCadastro.cadastro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {


    private Long id;
    @NotNull(message = "nome não pode ser nulo")
    @NotEmpty(message = "nome não pode ser vazio")
    private String nome;
    @NotNull(message = "nascimento não pode ser nulo")
    private LocalDate nascimento;
    @NotNull(message = "email não pode ser nulo")
    @NotEmpty(message = "email não pode ser vazio")
    @Email
    private String email;
    @CPF
    @NotNull(message = "cpf não pode ser nulo")
    @NotEmpty(message = "cpf não pode ser vazio")
    private String cpf;
}
