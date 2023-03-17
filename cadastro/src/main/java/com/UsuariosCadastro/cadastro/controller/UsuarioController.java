package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> buscarTodos() {
        return ResponseEntity.ok(service.modelList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UsuarioModel>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarPorID(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody @Valid UsuarioModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastro(model));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioModel> alterar(@RequestBody @Valid UsuarioModel model) {
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastro(model));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletarCadastro(@PathVariable(value = "id") Long id) {
        service.deletarUsuario(id);
        return HttpStatus.OK;
    }
}
