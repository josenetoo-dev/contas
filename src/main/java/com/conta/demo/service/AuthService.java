package com.conta.demo.service;

import com.conta.demo.dto.auth.LoginRequest;
import com.conta.demo.dto.auth.LoginResponse;
import com.conta.demo.dto.auth.RegisterRequest;
import com.conta.demo.dto.auth.UsuarioResponse;
import com.conta.demo.exception.conflit.SenhaInvalidaException;
import com.conta.demo.exception.conflit.UsuarioJaExiste;
import com.conta.demo.exception.notfound.UsuarioNaoEncontrado;
import com.conta.demo.model.Usuario;
import com.conta.demo.repository.UsuarioRepository;
import com.conta.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Registrar e Login

    // registrar
    public UsuarioResponse registrar(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new UsuarioJaExiste("Usuario já existe");
        }

        String senhaCriptografada = passwordEncoder.encode(request.getSenha());

        Usuario usuario = new Usuario(
                request.getUsername(),
                senhaCriptografada
        );

        return new UsuarioResponse(usuarioRepository.save(usuario));
    }

    // login
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsuarioNaoEncontrado("Usuario não encontrado"));

        boolean senhaCorreta = passwordEncoder.matches(
                request.getSenha(),
                usuario.getSenha());

        if (!senhaCorreta) {
            throw new SenhaInvalidaException("Senha inválida");
        }

        String token = jwtUtil.gerarToken(usuario.getUsername());

        return new LoginResponse(token);
    }
}
