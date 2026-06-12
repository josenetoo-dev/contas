package com.conta.demo.repository;

import com.conta.demo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    boolean existsByEmailId(Long emailId);
}