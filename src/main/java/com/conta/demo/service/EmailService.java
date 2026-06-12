package com.conta.demo.service;

import com.conta.demo.dto.email.EmailRequest;
import com.conta.demo.dto.email.EmailResponse;
import com.conta.demo.exception.conflit.EmailComContasCadastradasException;
import com.conta.demo.exception.conflit.EmailJaCadastradoException;
import com.conta.demo.exception.notfound.EmailNaoEncontradoException;
import com.conta.demo.model.Email;
import com.conta.demo.repository.ContaRepository;
import com.conta.demo.repository.EmailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final ContaRepository contaRepository;

    public EmailService(EmailRepository emailRepository, ContaRepository contaRepository) {
        this.emailRepository = emailRepository;
        this.contaRepository = contaRepository;
    }

    // Verificação de Id
    @Transactional(readOnly = true)
    public Email verificarId(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EmailNaoEncontradoException("Email não encontrado"));
    }

    // create
    @Transactional
    public EmailResponse create(EmailRequest request) {
        if (emailRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        Email email = new Email(
                request.getEmail(),
                request.getSenha()
        );

        return new EmailResponse(emailRepository.save(email));
    }


    // read
    @Transactional
    public List<EmailResponse> read() {
        return emailRepository.findAll()
                .stream()
                .map(EmailResponse::new)
                .toList();
    }

    // Update
    @Transactional
    public EmailResponse update(Long id, EmailRequest request) {
        Email email = verificarId(id);

        if (emailRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        email.setEmail(request.getEmail());
        email.setSenha(request.getSenha());

        return new EmailResponse(emailRepository.save(email));
    }

    // Delete
    @Transactional
    public void delete(Long id) {
        Email email = verificarId(id);

        if(contaRepository.existsByEmailId(id)) {
            throw new EmailComContasCadastradasException("Não é possível deletar este email, pois existem contas cadastradas nele");
        }

        emailRepository.delete(email);
    }

    // Buscar por id
    @Transactional(readOnly = true)
    public EmailResponse buscarPorId(Long id) {
        return new EmailResponse(verificarId(id));
    }
}
