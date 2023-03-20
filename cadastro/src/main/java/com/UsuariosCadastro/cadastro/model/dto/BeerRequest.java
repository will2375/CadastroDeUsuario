package com.UsuariosCadastro.cadastro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerRequest {

    private Long id;
    @JsonProperty("name")
    private String nome;
    @JsonProperty("description")
    private String descricao;
    @JsonProperty("abv")
    private Long porcentagemDeAlcool;
    @JsonProperty("ibu")
    private Long armagor;
}
