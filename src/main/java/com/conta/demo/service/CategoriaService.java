package com.conta.demo.service;

import com.conta.demo.dto.categoria.CategoriaRequest;
import com.conta.demo.dto.categoria.CategoriaResponse;
import com.conta.demo.exception.conflit.CategoriaComAplicacoesException;
import com.conta.demo.exception.conflit.NomeJaExisteException;
import com.conta.demo.exception.notfound.CategoriaNaoEncontradaException;
import com.conta.demo.model.Categoria;
import com.conta.demo.repository.AplicacaoRepository;
import com.conta.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final AplicacaoRepository aplicacaoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, AplicacaoRepository aplicacaoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.aplicacaoRepository = aplicacaoRepository;
    }

    // verificação de id
    public Categoria verificarId(Long id)  {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada"));
    }

    // create
    @Transactional
    public CategoriaResponse create(CategoriaRequest request) {
        if (categoriaRepository.existsByNome(request.getNome())) {
            throw new NomeJaExisteException("Nome já existe");
        }

        Categoria categoria = new Categoria(
                request.getNome()
        );

        return new CategoriaResponse(categoriaRepository.save(categoria));
    }

    // read
    @Transactional(readOnly = true)
    public List<CategoriaResponse> read() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaResponse::new)
                .toList();
    }

    // update
    @Transactional
    public CategoriaResponse update(Long id, CategoriaRequest request) {
        Categoria categoria = verificarId(id);

        if (categoriaRepository.existsByNomeAndIdNot(request.getNome(), id)) {
            throw new NomeJaExisteException("Nome já existe");
        }

        categoria.setNome(request.getNome());

        return new CategoriaResponse(categoriaRepository.save(categoria));
    }


    // delete
    @Transactional
    public void delete(Long id) {
        Categoria categoria = verificarId(id);

        if (aplicacaoRepository.existsByCategoriaId(categoria.getId())) {
            throw new CategoriaComAplicacoesException("Não é possível excluir esta categoria, pois existem aplicações cadastradas nela");
        }

        categoriaRepository.delete(categoria);
    }

    // buscar por id
    @Transactional(readOnly = true)
    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = verificarId(id);
        return new CategoriaResponse(categoria);
    }

    // Buscar por nome
    @Transactional(readOnly = true)
    public CategoriaResponse buscarPorNome(String nome) {
        return new CategoriaResponse(categoriaRepository.findByNome(nome)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada")));
    }
}
