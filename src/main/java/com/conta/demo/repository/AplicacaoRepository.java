package com.conta.demo.repository;

import com.conta.demo.model.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {
    boolean existsByCategoriaId(Long id);
}
