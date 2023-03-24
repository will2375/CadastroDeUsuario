package com.UsuariosCadastro.cadastro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String cpf;
    @Column
    private String cerveja;

//    @JsonIgnore
//    @OneToMany(mappedBy = "addBeer", cascade = CascadeType.ALL)
//    private List<BeerModel> beerModel;

}
