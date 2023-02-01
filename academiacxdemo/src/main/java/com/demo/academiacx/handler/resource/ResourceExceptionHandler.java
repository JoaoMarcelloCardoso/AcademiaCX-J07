package com.demo.academiacx.handler.resource;

import com.demo.academiacx.handler.ConflitoRecursoException;
import com.demo.academiacx.handler.DetalhesErro;
import com.demo.academiacx.handler.ParametroInvalidoException;
import com.demo.academiacx.handler.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerRecursoNaoEncontradoException(RecursoNaoEncontradoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l, 404l, System.currentTimeMillis(), "http://localhost:8080/erros/404"));

    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerParametroInvalidoException(ParametroInvalidoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l, System.currentTimeMillis(), "http://localhost:8080/erros/406"));

    }

    @ExceptionHandler(ConflitoRecursoException.class)
    public ResponseEntity<DetalhesErro> handlerConflitoRecursoException(ConflitoRecursoException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DetalhesErro(e.getMessage(), 409l, 409l, System.currentTimeMillis(), "http://localhost:8080/erros/409"));

    }
}
