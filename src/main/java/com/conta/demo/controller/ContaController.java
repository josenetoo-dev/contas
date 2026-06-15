package com.conta.demo.controller;

import com.conta.demo.dto.conta.ContaRequest;
import com.conta.demo.dto.conta.ContaResponse;
import com.conta.demo.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContaResponse> create(@Valid @RequestBody ContaRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ContaResponse>> read() {
        return ResponseEntity.ok(service.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> update(@PathVariable Long id, @Valid @RequestBody ContaRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{emailId}")
    public ResponseEntity<List<ContaResponse>> buscarPorEmail(@PathVariable Long emailId) {
        return ResponseEntity.ok(service.buscarPorEmail(emailId));
    }

    @GetMapping("/aplicacao/{aplicacaoId}")
    public ResponseEntity<List<ContaResponse>> buscarPorAplicacao(@PathVariable Long aplicacaoId) {
        return ResponseEntity.ok(service.buscarPorAplicacao(aplicacaoId));
    }

    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<ContaResponse>> buscarPorCategoria(@PathVariable String nome) {
        return ResponseEntity.ok(service.buscarPorCategoria(nome));
    }
}