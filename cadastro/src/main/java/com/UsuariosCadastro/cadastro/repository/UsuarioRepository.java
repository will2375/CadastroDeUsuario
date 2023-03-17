package com.UsuariosCadastro.cadastro.repository;

import com.UsuariosCadastro.cadastro.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

     UsuarioModel findByEmail(String email);
     UsuarioModel findByCpf(String cpf);
}
