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
    public ResponseEntity<DetalhesErro> handlerRecursoNaoEncontradoExeception(RecursoNaoEncontradoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l, 404l, System.currentTimeMillis(), "http://localhost:8080/erros/404"));
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerParametroInvalidoException(ParametroInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(), "http://localhost:8080/erros/406"));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<DetalhesErro> handlerBadRequestException(BadRequestException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetalhesErro(e.getMessage(), 400l, 400l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }



    @ExceptionHandler(ForeignKeyException.class)
    public ResponseEntity<DetalhesErro> handlerForeignKeyException(ForeignKeyException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DetalhesErro(e.getMessage(), 409l, 409l, System.currentTimeMillis(), "http://localhost:8080/erros/409"));

    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l, 404l, System.currentTimeMillis(), "http://localhost:8080/erros/404"));
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<DetalhesErro> handlerSenhaInvalidaException(SenhaInvalidaException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(),"http://localhost:8080/erros/400"));
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerCpfInvalidoException(CpfInvalidoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(), "http://localhost:8080/erros/400"));
    }
}
