package com.conta.demo.service;

import com.conta.demo.dto.conta.ContaRequest;
import com.conta.demo.dto.conta.ContaResponse;
import com.conta.demo.exception.conflit.ContaJaExisteException;
import com.conta.demo.exception.notfound.AplicacaoNaoEncontradaException;
import com.conta.demo.exception.notfound.ContaNaoEncontradaException;
import com.conta.demo.exception.notfound.EmailNaoEncontradoException;
import com.conta.demo.model.Aplicacao;
import com.conta.demo.model.Conta;
import com.conta.demo.model.Email;
import com.conta.demo.repository.AplicacaoRepository;
import com.conta.demo.repository.ContaRepository;
import com.conta.demo.repository.EmailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final AplicacaoRepository aplicacaoRepository;
    private final EmailRepository emailRepository;

    public ContaService(ContaRepository contaRepository, AplicacaoRepository aplicacaoRepository, EmailRepository emailRepository) {
        this.contaRepository = contaRepository;
        this.aplicacaoRepository = aplicacaoRepository;
        this.emailRepository = emailRepository;
    }

    private Conta verificarId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }

    // create
    @Transactional
    public ContaResponse create(ContaRequest request) {
        if (contaRepository.existsByAplicacaoIdAndEmailIdAndNomeUsuario(
                request.getAplicacaoId(),
                request.getEmailId(),
                request.getNomeDeUsuario()
        )) {
            throw new ContaJaExisteException("Já existe uma conta cadastrada para essa aplicação com esse email e usuario");
        }

        Aplicacao aplicacao = aplicacaoRepository.findById(request.getAplicacaoId())
                .orElseThrow(() -> new AplicacaoNaoEncontradaException("Aplicação não encontrada"));

        Email email = emailRepository.findById(request.getEmailId())
                .orElseThrow(() -> new EmailNaoEncontradoException("Email não encontrado"));

        Conta conta = new Conta(
                aplicacao,
                email,
                request.getNomeDeUsuario(),
                request.getSenha()
        );

        return new ContaResponse(contaRepository.save(conta));
    }

    // read
    @Transactional(readOnly = true)
    public List<ContaResponse> read() {
        return contaRepository.findAll()
                .stream()
                .map(ContaResponse::new)
                .toList();
    }

    // update
    @Transactional
    public ContaResponse update(Long id, ContaRequest request) {
        Conta conta = verificarId(id);

        if (contaRepository.existsByAplicacaoIdAndEmailIdAndNomeUsuarioAndIdNot(
                request.getAplicacaoId(),
                request.getEmailId(),
                request.getNomeDeUsuario(),
                id
        )) {
            throw new ContaJaExisteException("Já existe uma conta cadastrada para essa aplicação com esse email e usuario");
        }

        Email email = emailRepository.findById(request.getEmailId())
                .orElseThrow(() -> new EmailNaoEncontradoException("Email não encontrado"));

        conta.setEmail(email);
        conta.setNomeUsuario(request.getNomeDeUsuario());
        conta.setSenha(request.getSenha());

        return new ContaResponse(contaRepository.save(conta));
    }

    // delete
    @Transactional
    public void delete(Long id) {
        Conta conta = verificarId(id);

        contaRepository.delete(conta);
    }


    // buscar por id
    @Transactional(readOnly = true)
    public ContaResponse buscarPorId(Long id) {
        return new ContaResponse(verificarId(id));
    }

    // Buscar por email id
    @Transactional(readOnly = true)
    public List<ContaResponse> buscarPorEmail(Long emailId) {
        return contaRepository.findByEmailId(emailId)
                .stream()
                .map(ContaResponse::new)
                .toList();
    }

    // Buscar contas por aplicacao id
    @Transactional(readOnly = true)
    public List<ContaResponse> buscarPorAplicacao(Long aplicacaoId) {
        return contaRepository.findByAplicacaoId(aplicacaoId)
                .stream()
                .map(ContaResponse::new)
                .toList();
    }

    // buscar por Categoria
    @Transactional(readOnly = true)
    public List<ContaResponse> buscarPorCategoria(String nome) {
        return contaRepository.findByAplicacaoCategoriaNomeIgnoreCase(nome)
                .stream()
                .map(ContaResponse::new)
                .toList();
    }
}
