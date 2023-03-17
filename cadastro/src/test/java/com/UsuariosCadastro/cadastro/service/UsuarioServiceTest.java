package com.UsuariosCadastro.cadastro.service;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
import com.UsuariosCadastro.cadastro.service.exceptions.UsuarioNaoEncontrado;
import com.UsuariosCadastro.cadastro.service.exceptions.ValidacaoDeDuplicidade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceTest {
    public static final String USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO = "Usuario não existe ou não encontrado";
    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private UsuarioModel model;

    private Optional<UsuarioModel> optionalUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void cadastroOuUpdate() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(model);

        UsuarioModel resposta = service.cadastro(model);

        assertNotNull(resposta);
        assertEquals(UsuarioModel.class, resposta.getClass());
        assertEquals(model.getCodigo(), resposta.getCodigo());
    }

    @Test
    void emailRepitido() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(model);

        try {
            optionalUser.get().setCodigo(2L);
            service.cadastro(model);
        } catch (Exception e) {
            assertEquals(ValidacaoDeDuplicidade.class, e.getClass());
            assertEquals("EMAIL ja cadastrado", e.getMessage());
        }
    }

    @Test
    void cpfRepitido() {
        Mockito.when(repository.findByCpf(Mockito.anyString())).thenReturn(model);

        try {
            optionalUser.get().setCodigo(2L);
            optionalUser.get().setEmail("abc@123.com");
            service.cadastro(model);
        } catch (Exception e) {
            assertEquals(ValidacaoDeDuplicidade.class, e.getClass());
            assertEquals("CPF ja cadastrado", e.getMessage());
        }
    }

    @Test
    void buscarPorId() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        Optional<UsuarioModel> resposta = service.repository.findById(optionalUser.get().getCodigo());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(Optional.class, resposta.getClass());
        Assertions.assertEquals(optionalUser.get().getCodigo(), resposta.get().getCodigo());
    }

    @Test
    void idNaoEncontrado() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new UsuarioNaoEncontrado(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO));

        try {
            service.buscarPorID(optionalUser.get().getCodigo());
        } catch (Exception exception) {
            assertEquals(UsuarioNaoEncontrado.class, exception.getClass());
            assertEquals(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO, exception.getMessage());
        }
    }

    @Test
    void modelList() {
        Mockito.when(repository.findAll()).thenReturn(List.of(model));

        List<UsuarioModel> resposta = service.modelList();

        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(UsuarioModel.class, resposta.get(0).getClass());
        assertEquals(model.getCodigo(), resposta.get(0).getCodigo());
    }

    @Test
    void deletarUsuario() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyLong());
        service.deletarUsuario(optionalUser.get().getCodigo());
        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void erroDeleteUsuario() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new UsuarioNaoEncontrado(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO));

        try {
            service.deletarUsuario(optionalUser.get().getCodigo());
        }catch (Exception e) {
            assertEquals(UsuarioNaoEncontrado.class, e.getClass());
            assertEquals(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO, e.getMessage());
        }
    }

    private void startUser() {
        model = new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883");
        optionalUser = Optional.of(new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883"));
    }
}