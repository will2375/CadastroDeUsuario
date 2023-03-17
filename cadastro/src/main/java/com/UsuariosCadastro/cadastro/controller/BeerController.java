package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.intregacaoExterna.client.BeerClient;
import com.UsuariosCadastro.cadastro.model.BeerResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/beer")
public class BeerController {
    @Autowired
    private BeerClient feign;
    @GetMapping("{id}")
    public List<BeerResponse> buscarPorId(@PathVariable Long id) {
        return  feign.getBeerById(id);
    }

    @GetMapping("/random")
    public List<BeerResponse> buscarTodos() {
        return feign.getRandomBeer();
    }

}
