package com.UsuariosCadastro.cadastro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table
public class BeerModel {
    @Id
    private Long id;
    @Column
    private String nome;
    @Column
    private String descricao;
    @Column
    private Long porcentagemDeAlcool;
    @Column
    private Long armagor;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
//    private UsuarioModel addBeer;
}
