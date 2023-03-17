package com.UsuariosCadastro.cadastro.controller;

import com.UsuariosCadastro.cadastro.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsuarioController.class)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsuarioController controller;
    @MockBean
    private UsuarioService service;
    @Autowired
    private ObjectMapper mapper;
    @Test
    void buscarTodos() throws Exception {
      mockMvc.perform(get("/usuarios")).andExpect(status().isOk());
    }
    @Test
    void cadastrar() throws Exception {
        URI uri = new URI("/usuarios/cadastro");
        String json = "{\n" +
                "    \"nome\": \"william\",\n" +
                "    \"nascimento\" :\"1995-10-19\",\n" +
                "    \"email\": \"will@email.com.br\",\n" +
                "    \"cpf\": \"75265158014\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());
    }

    @Test
    void alterar() throws Exception {
        URI uri = new URI("/usuarios/{id}");
        String json = "{\n" +
                "    \"nome\": \"william\",\n" +
                "    \"nascimento\" :\"1995-10-19\",\n" +
                "    \"email\": \"will@email.com.br\",\n" +
                "    \"cpf\": \"75265158014\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.patch(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());
    }

    @Test
    void deletarCadastro() {
    }
}