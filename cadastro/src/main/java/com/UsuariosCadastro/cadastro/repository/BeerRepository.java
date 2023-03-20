package com.UsuariosCadastro.cadastro.repository;

import com.UsuariosCadastro.cadastro.model.BeerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<BeerModel, Long> {
}
