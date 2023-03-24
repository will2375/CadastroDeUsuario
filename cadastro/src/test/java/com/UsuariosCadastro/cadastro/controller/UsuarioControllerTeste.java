package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioRequest;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioResponse;
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

    private UsuarioRequest request;

    private UsuarioResponse response;

    private Optional<UsuarioModel> optionalUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        model = new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883", "breja");
        request = new UsuarioRequest(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883");
        optionalUser = Optional.of(new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883", "breja"));
        response = new UsuarioResponse(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "breja");
    }

    @Test
    void buscarTodos() {
        Mockito.when(service.modelList()).thenReturn(List.of(response));

        ResponseEntity<List<UsuarioResponse>> response = controller.buscarTodos();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioResponse.class, response.getBody().get(0).getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarPorId() {
        Mockito.when(service.buscarPorID(Mockito.anyLong())).thenReturn(model);

        UsuarioModel response = controller.buscarPorId(model.getId()).getBody();

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(UsuarioModel.class, response.getClass());
        assertEquals(model.getId(), response.getId());
    }

    @Test
    void cadastrar() {
        Mockito.when(service.cadastro(Mockito.any())).thenReturn(model);

        ResponseEntity<UsuarioResponse> response = controller.cadastrar(request);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void alterar() {
        Mockito.when(service.cadastro(model)).thenReturn(model);

        ResponseEntity<UsuarioResponse> response = controller.atualizar(request);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(model.getId(), response.getBody().getId());
    }

    @Test
    void deletarCadastro() {
        Mockito.doNothing().when(service).deletarUsuario(Mockito.anyLong());

        HttpStatus response = controller.deletarCadastro(model.getId());

        assertNotNull(response);
        Mockito.verify(service, Mockito.times(1)).deletarUsuario(Mockito.anyLong());
    }
}