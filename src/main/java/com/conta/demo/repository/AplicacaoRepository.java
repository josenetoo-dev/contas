package com.conta.demo.repository;

import com.conta.demo.model.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {
    boolean existsByCategoriaId(Long id);
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);
    Optional<Aplicacao> findByNome(String nome);
    List<Aplicacao> findByCategoriaNomeIgnoreCase(String nome);
}
