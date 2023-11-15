package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.intregacaoExterna.client.BeerClient;
import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioRequest;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioResponse;
import com.UsuariosCadastro.cadastro.model.factory.UsuarioRequestFactory;
import com.UsuariosCadastro.cadastro.model.factory.UsuarioResponseFactory;
import com.UsuariosCadastro.cadastro.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Api(value ="Api Rest Usuarios" )
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private BeerClient feign;

    @GetMapping
    @ApiOperation(value ="Retorna lista de usuarios")
    public ResponseEntity<List<UsuarioResponse>> buscarTodos() {
        return ResponseEntity.ok(service.modelList());
    }

    @GetMapping("{id}")
    @ApiOperation(value ="Retorna usuario por id")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorID(id));
    }

    @PostMapping("/cadastro")
    @ApiOperation(value ="cadastra novo usuario")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest request) {
        UsuarioModel model = UsuarioRequestFactory.criar(request);
        service.cadastro(model);
        UsuarioResponse response = UsuarioResponseFactory.criar(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value ="edita usuario por id")
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody @Valid UsuarioRequest request) {
        UsuarioModel model = UsuarioRequestFactory.criar(request);
        service.alterar(model);
        UsuarioResponse response = UsuarioResponseFactory.criar(model);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value ="deleta usuario por id")
    public HttpStatus deletarCadastro(@PathVariable(value = "id") Long id) {
        service.deletarUsuario(id);
        return HttpStatus.OK;
    }
}
