package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.intregacaoExterna.client.BeerClient;
import com.UsuariosCadastro.cadastro.model.dto.BeerRequest;
import com.UsuariosCadastro.cadastro.service.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/beer")
@Api(value ="Api Rest Cerveja" )
@CrossOrigin("*")
public class BeerController {

    @Autowired
    BeerService service;
    @Autowired
    private BeerClient feign;
    @GetMapping("{id}")
    @ApiOperation(value ="Busca cerveja por id")
    public List<BeerRequest> buscarPorId(@PathVariable Long id) {
        return  feign.getBeerById(id);
    }

    @GetMapping("/random")
    @ApiOperation(value ="Busca cerveja aleatoria")
    public List<BeerRequest> buscarTodos() {
        return feign.getRandomBeer();
    }

}
