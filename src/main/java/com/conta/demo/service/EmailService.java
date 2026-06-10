package com.conta.demo.service;

import com.conta.demo.repository.EmailRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    // CRUD

    // create


}
