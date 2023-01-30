package com.commerce.acdemycx.handler.resource;

import com.commerce.acdemycx.handler.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExeceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoExeception.class)
    public ResponseEntity<DetalhesErro> handlerRecursoNaoEncontradoExeception(RecursoNaoEncontradoExeception e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l, 404l, System.currentTimeMillis(), "http://localhost:8080/erros/404"));
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerParametroInvalidoException(ParametroInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(), "http://localhost:8080/erros/406"));
    }

    @ExceptionHandler(ErroDeFormatoException.class)
    public ResponseEntity<DetalhesErro> handlerErroDeFormatoException(ErroDeFormatoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));

    }


    @ExceptionHandler(SemConteudoException.class)
    public ResponseEntity<DetalhesErro> handlerSemConteudoException(SemConteudoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new DetalhesErro(e.getMessage(), 204l, 204l, System.currentTimeMillis(), "http://localhost:8080/erros/204"));
    }

    @ExceptionHandler(UsuarioExistente.class)
    public ResponseEntity<DetalhesErro> handlerParametroInvalidoException(UsuarioExistente e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(), "http://localhost:8080/erros/406"));
    }

}
