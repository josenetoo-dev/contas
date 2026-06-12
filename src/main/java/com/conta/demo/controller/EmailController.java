package com.conta.demo.controller;

import com.conta.demo.dto.email.EmailRequest;
import com.conta.demo.dto.email.EmailResponse;
import com.conta.demo.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {
    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmailResponse> createEmail(@Valid @RequestBody EmailRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<EmailResponse>> readEmail() {
        return ResponseEntity
                .ok(service.read());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailResponse> updateEmail(@PathVariable Long id, @RequestBody @Valid EmailRequest request) {
        return ResponseEntity
                .ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailResponse> buscarPorId (@PathVariable Long id) {
        return ResponseEntity
                .ok(service.buscarPorId(id));
    }

}
