package com.conta.demo.exception;

import com.conta.demo.exception.conflit.*;
import com.conta.demo.exception.notfound.AplicacaoNaoEncontradaException;
import com.conta.demo.exception.notfound.CategoriaNaoEncontradaException;
import com.conta.demo.exception.notfound.EmailNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            AplicacaoComContasException.class,
            CategoriaComContaException.class,
            CategoriaJaExisteException.class,
            EmailComContasCadastradasException.class,
            EmailJaCadastradoException.class,
            NomeJaExisteException.class
    })
    public ResponseEntity<ErroResponse> tratarConflitos(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErroResponse(409, ex.getMessage()));
    }

    @ExceptionHandler({
            EmailNaoEncontradoException.class,
            CategoriaNaoEncontradaException.class,
            AplicacaoNaoEncontradaException.class
    })
    public ResponseEntity<ErroResponse> tratarNaoEncontrado(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroResponse(404, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(new ErroResponse(400, ex.getMessage()));
    }
}
