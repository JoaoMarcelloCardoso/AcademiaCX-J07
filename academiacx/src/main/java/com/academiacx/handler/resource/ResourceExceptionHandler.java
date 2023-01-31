package com.academiacx.handler.resource;

import com.academiacx.handler.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerRecursoNaoEncontradoException(RecursoNaoEncontradoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l, 404l, System.currentTimeMillis(), "http://localhost:8080/erros/404"));
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerParametroInvalidoException(ParametroInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(), "http://localhost:8080/erros/406"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DetalhesErro> handlerConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DetalhesErro(e.getMessage(), 409l, 409l, System.currentTimeMillis(), "http://localhost:8080/erros/409"));
    }

    @ExceptionHandler(ParametroNullException.class)
    public ResponseEntity<DetalhesErro> handlerParametroNullException(ParametroNullException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerCpfInvalidoException(CpfInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(TamanhoInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerTamanhoInvalidoException(TamanhoInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(IdInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerIdInvalidoException(IdInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(NumeroInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerNumeroInvalidoException(NumeroInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(TelefoneInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerTelefoneInvalidoException(TelefoneInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerEmailInvalidoException(EmailInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(ErroAoConcluirPedidoException.class)
    public ResponseEntity<DetalhesErro> handlerErroAoConcluirPedidoException(ErroAoConcluirPedidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new DetalhesErro(e.getMessage(), 422l, 422l, System.currentTimeMillis(), "http://localhost:8080/erros/422"));
    }

    @ExceptionHandler(ErroAoAtualizarPedidoException.class)
    public ResponseEntity<DetalhesErro> handlerErroAoAtualizarPedidoException(ErroAoAtualizarPedidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new DetalhesErro(e.getMessage(), 422l, 422l, System.currentTimeMillis(), "http://localhost:8080/erros/422"));
    }

}
