package com.conta.demo.controller;

import com.conta.demo.dto.aplicacao.AplicacaoRequest;
import com.conta.demo.dto.aplicacao.AplicacaoResponse;
import com.conta.demo.service.AplicacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/aplicacoes")
public class AplicacaoController {

    private final AplicacaoService service;

    public AplicacaoController(AplicacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AplicacaoResponse> createAplicacao(@Valid @RequestBody AplicacaoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<AplicacaoResponse>> readAplicacoes(
            @PageableDefault(size = 10, sort = "id")Pageable pageable) {
        return ResponseEntity.ok(service.read(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AplicacaoResponse> updateAplicacao(@PathVariable Long id, @Valid @RequestBody AplicacaoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAplicacao(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AplicacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<AplicacaoResponse>> buscarPorNomeDaCategoria(@PathVariable String nome) {
        return ResponseEntity.ok(service.buscarPorNomeDaCategoria(nome));
    }

    @GetMapping("/aplicacao/{nome}")
    public ResponseEntity<AplicacaoResponse> buscarPorNomeDaAplicacao(@PathVariable String nome) {
        return ResponseEntity.ok(service.buscarPorNomeDaAplicacao(nome));
    }

}
