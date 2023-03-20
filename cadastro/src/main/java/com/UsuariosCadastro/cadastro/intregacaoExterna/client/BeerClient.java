package com.UsuariosCadastro.cadastro.intregacaoExterna.client;

import com.UsuariosCadastro.cadastro.model.dto.BeerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BeerClient", url = "https://api.punkapi.com/v2/beers")
public interface BeerClient {

    @GetMapping(value = "/random")
    List<BeerRequest> getRandomBeer();

    @GetMapping(value = "/{id}")
    List<BeerRequest> getBeerById(@PathVariable("id") Long id);
}
