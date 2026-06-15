package com.conta.demo.repository;

import com.conta.demo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    boolean existsByEmailId(Long emailId);
    boolean existsByAplicacaoId(Long id);

    boolean existsByAplicacaoIdAndEmailIdAndNomeUsuario(
            Long aplicacaoId,
            Long emailId,
            String nomeUsuario
    );

    boolean existsByAplicacaoIdAndEmailIdAndNomeUsuarioAndIdNot(
            Long aplicacaoId,
            Long emailId,
            String nomeUsuario,
            Long id
    );


    List<Conta> findByEmailId(Long id);

    List<Conta> findByAplicacaoId(Long id);

    List<Conta> findByAplicacaoCategoriaNomeIgnoreCase(String nome);
}