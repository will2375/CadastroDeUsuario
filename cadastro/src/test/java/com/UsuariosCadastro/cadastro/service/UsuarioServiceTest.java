package com.UsuariosCadastro.cadastro.service;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import com.UsuariosCadastro.cadastro.model.dto.UsuarioResponse;
import com.UsuariosCadastro.cadastro.repository.UsuarioRepository;
import com.UsuariosCadastro.cadastro.exception.BadRequestException;
import com.UsuariosCadastro.cadastro.exception.ValidacaoDeDuplicidade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        model = new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883");
        optionalUser = Optional.of(new UsuarioModel(3L, "william", LocalDate.of(1995, 10, 19), "will@em.com", "32439582883"));

    }
    @Test
    void cadastroOuUpdate() {
        when(repository.save(any())).thenReturn(model);

        UsuarioModel resposta = service.cadastro(model);

        assertNotNull(resposta);
        assertEquals(UsuarioModel.class, resposta.getClass());
        assertEquals(model.getId(), resposta.getId());
    }

    @Test
    void emailRepitido() {
        when(repository.findByEmail(anyString())).thenReturn(model);
        optionalUser.get().setId(2L);

        var erro= assertThrows(ValidacaoDeDuplicidade.class,() ->{
            service.cadastro(model);
        });
        assertEquals("EMAIL ja cadastrado", erro.getMessage());
    }

    @Test
    void cpfRepitido() {
        when(repository.findByCpf(anyString())).thenReturn(model);

        try {
            optionalUser.get().setId(2L);
            optionalUser.get().setEmail("abc@123.com");
            service.cadastro(model);
        } catch (Exception e) {
            assertEquals(ValidacaoDeDuplicidade.class, e.getClass());
            assertEquals("CPF ja cadastrado", e.getMessage());
        }
    }

    @Test
    void buscarPorId() {
        when(repository.findById(anyLong())).thenReturn(optionalUser);
        Optional<UsuarioModel> resposta = service.repository.findById(optionalUser.get().getId());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(Optional.class, resposta.getClass());
        Assertions.assertEquals(optionalUser.get().getId(), resposta.get().getId());
    }

    @Test
    void idNaoEncontrado() {
        when(repository.findById(anyLong())).thenThrow(new BadRequestException(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO));

        try {
            service.buscarPorID(optionalUser.get().getId());
        } catch (Exception exception) {
            assertEquals(BadRequestException.class, exception.getClass());
            assertEquals(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO, exception.getMessage());
        }
    }

    @Test
    void modelList() {
        when(repository.findAll()).thenReturn(List.of(model));

        List<UsuarioResponse> resposta = service.modelList();

        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(UsuarioResponse.class, resposta.get(0).getClass());
        assertEquals(model.getId(), resposta.get(0).getId());
    }

    @Test
    void deletarUsuario() {
        when(repository.findById(anyLong())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyLong());
        service.deletarUsuario(optionalUser.get().getId());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void erroDeleteUsuario() {
        when(repository.findById(anyLong())).thenThrow(new BadRequestException(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO));

        try {
            service.deletarUsuario(optionalUser.get().getId());
        }catch (Exception e) {
            assertEquals(BadRequestException.class, e.getClass());
            assertEquals(USUARIO_NAO_EXISTE_OU_NAO_ENCONTRADO, e.getMessage());
        }
    }
}