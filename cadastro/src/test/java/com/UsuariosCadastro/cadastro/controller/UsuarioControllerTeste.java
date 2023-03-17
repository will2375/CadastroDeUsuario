package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTeste {


    @InjectMocks
    private UsuarioController controller;

    @Mock
    private UsuarioService service;

    private UsuarioModel model;

    private Optional<UsuarioModel> optionalUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void buscarTodos() {
        Mockito.when(service.modelList()).thenReturn(List.of(model));

        ResponseEntity<List<UsuarioModel>> response = controller.buscarTodos();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioModel.class, response.getBody().get(0).getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorId() {
        Mockito.when(service.buscarPorID(Mockito.anyLong())).thenReturn(optionalUser);

        Optional<UsuarioModel> response = controller.buscarPorId(model.getCodigo()).getBody();

        assertNotNull(response);
        assertNotNull(response.get().getCodigo());
        assertEquals(Optional.class, response.getClass());
        assertEquals(optionalUser.get().getCodigo(), response.get().getCodigo());
    }

    @Test
    void cadastrar() {
        Mockito.when(service.cadastro(Mockito.any())).thenReturn(model);

        ResponseEntity<UsuarioModel> response = controller.cadastrar(model);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void alterar() {
        Mockito.when(service.cadastro(model)).thenReturn(model);

        ResponseEntity<UsuarioModel> response = controller.alterar(model);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(model.getCodigo(), response.getBody().getCodigo());
    }

    @Test
    void deletarCadastro() {
        Mockito.doNothing().when(service).deletarUsuario(Mockito.anyLong());

        HttpStatus response = controller.deletarCadastro(model.getCodigo());

        assertNotNull(response);
        Mockito.verify(service, Mockito.times(1)).deletarUsuario(Mockito.anyLong());
    }

    private void startUser() {
        model = new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883");
        optionalUser = Optional.of(new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883"));
    }
}