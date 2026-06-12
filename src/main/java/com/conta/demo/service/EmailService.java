package com.conta.demo.service;

import com.conta.demo.dto.email.EmailRequest;
import com.conta.demo.dto.email.EmailResponse;
import com.conta.demo.exception.conflit.EmailJáCadastradoException;
import com.conta.demo.exception.notfound.EmailNaoEncontradoException;
import com.conta.demo.model.Email;
import com.conta.demo.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    // Verificação de Id
    public Email verificarId(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EmailNaoEncontradoException("Email não encontrado"));
    }

    // create
    public EmailResponse create(EmailRequest request) {
        if (emailRepository.existsByEmail(request.getEmail())) {
            throw new EmailJáCadastradoException("Email já cadastrado");
        }

        Email email = new Email(
                request.getEmail(),
                request.getSenha()
        );

        return new EmailResponse(emailRepository.save(email));
    }


    // read
    public List<EmailResponse> read() {
        return emailRepository.findAll()
                .stream()
                .map(EmailResponse::new)
                .toList();
    }

    // Update
    public EmailResponse update(Long id, EmailRequest request) {
        Email email = verificarId(id);

        if (emailRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new EmailJáCadastradoException("Email já cadastrado");
        }

        email.setEmail(request.getEmail());
        email.setSenha(request.getSenha());

        return new EmailResponse(emailRepository.save(email));
    }

    // Delete
    public void delete(Long id) {
        Email email = verificarId(id);

        emailRepository.delete(email);
    }

    // Buscar por id
    public EmailResponse buscarPorId(Long id) {
        Email email = verificarId(id);

        return new EmailResponse(email);
    }
}
