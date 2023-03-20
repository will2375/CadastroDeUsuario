package com.UsuariosCadastro.cadastro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private LocalDate nascimento;
    @Column(unique = true) @Email
    private String email;
    @Column(unique = true) @CPF
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "addBeer", cascade = CascadeType.ALL)
    private List<BeerModel> beerModel;

    public UsuarioModel(long l, String william, LocalDate of, String s, String s1) {

    }
}
