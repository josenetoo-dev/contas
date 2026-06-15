package com.conta.demo.service;

import com.conta.demo.dto.aplicacao.AplicacaoRequest;
import com.conta.demo.dto.aplicacao.AplicacaoResponse;
import com.conta.demo.exception.conflit.AplicacaoComContasException;
import com.conta.demo.exception.conflit.NomeJaExisteException;
import com.conta.demo.exception.notfound.AplicacaoNaoEncontradaException;
import com.conta.demo.exception.notfound.CategoriaNaoEncontradaException;
import com.conta.demo.model.Aplicacao;
import com.conta.demo.model.Categoria;
import com.conta.demo.repository.AplicacaoRepository;
import com.conta.demo.repository.CategoriaRepository;
import com.conta.demo.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AplicacaoService {

    private final AplicacaoRepository aplicacaoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ContaRepository contaRepository;

    public AplicacaoService(AplicacaoRepository aplicacaoRepository, CategoriaRepository categoriaRepository, ContaRepository contaRepository) {
        this.aplicacaoRepository = aplicacaoRepository;
        this.categoriaRepository = categoriaRepository;
        this.contaRepository = contaRepository;
    }

    // verificar id
    @Transactional(readOnly = true)
    public Aplicacao verificarId(Long id)  {
        return aplicacaoRepository.findById(id)
                .orElseThrow(() -> new AplicacaoNaoEncontradaException("Aplicação não encontrada"));
    }

    // create
    @Transactional
    public AplicacaoResponse create(AplicacaoRequest request) {
        if (aplicacaoRepository.existsByNome(request.getNome())) {
            throw new NomeJaExisteException("Já existe uma aplicação com esse nome");
        }

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada"));

        Aplicacao aplicacao = new Aplicacao(
                request.getNome(),
                categoria
        );

        return new AplicacaoResponse(aplicacaoRepository.save(aplicacao));
    }

    // read
    public List<AplicacaoResponse> read() {
        return aplicacaoRepository.findAll()
                .stream()
                .map(AplicacaoResponse::new)
                .toList();
    }

    // update
    @Transactional
    public AplicacaoResponse update(Long id, AplicacaoRequest request) {
        Aplicacao aplicacao = verificarId(id);

        if (aplicacaoRepository.existsByNomeAndIdNot(request.getNome(), id)) {
            throw new NomeJaExisteException("Já existe aplicação com esse nome");
        }

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada"));

        aplicacao.setNome(request.getNome());
        aplicacao.setCategoria(categoria);

        return new AplicacaoResponse(aplicacaoRepository.save(aplicacao));
    }

    // delete
    @Transactional
    public void delete(Long id) {
        Aplicacao aplicacao = verificarId(id);

        if (contaRepository.existsByAplicacaoId(id)) {
            throw new AplicacaoComContasException("Não é possível excluir esta aplicação, pois existem contas cadastradas nela");
        }

        aplicacaoRepository.delete(aplicacao);
    }

    // buscar por id  / buscar por categoria / buscar por nome da aplicação

    // buscar por id
    public AplicacaoResponse buscarPorId(Long id) {
        Aplicacao aplicacao = verificarId(id);

        return new AplicacaoResponse(aplicacao);
    }

    // buscar por categoria
    @Transactional(readOnly = true)
    public List<AplicacaoResponse> buscarPorNomeDaCategoria(String nome) {

        if (!categoriaRepository.existsByNome(nome)) {
            throw new CategoriaNaoEncontradaException("Categoria não encontrada");
        }

        return aplicacaoRepository.findByCategoriaNomeIgnoreCase(nome)
                .stream()
                .map(AplicacaoResponse::new)
                .toList();
    }

    // buscar por nome aplicação
    @Transactional(readOnly = true)
    public AplicacaoResponse buscarPorNomeDaAplicacao(String nome) {
        Aplicacao aplicacao = aplicacaoRepository.findByNome(nome)
                .orElseThrow(() -> new AplicacaoNaoEncontradaException("Aplicação não encontrada"));

        return new AplicacaoResponse(aplicacao);
    }
}
