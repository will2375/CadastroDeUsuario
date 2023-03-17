package com.UsuariosCadastro.cadastro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerResponse {

    private Long id;
    private String name;
    private String description;
    private Long abv;
    private Long ibu;
}
