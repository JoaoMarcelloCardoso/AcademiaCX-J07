package com.example.academiacx2.handler.resource;

import com.example.academiacx2.handler.exceptions.AcessoNaoAutorizadoException;
import com.example.academiacx2.handler.exceptions.DetalhesErro;
import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;
import com.example.academiacx2.handler.exceptions.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerRecursoNaoEncontradoException(RecursoNaoEncontradoException e, HttpServletRequest request){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l,404l, System.currentTimeMillis(), "http://localhost:3606/errors/404"));
    }

    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<DetalhesErro> handlerParametroInvalidoException(RecursoNaoEncontradoException e, HttpServletRequest request){
        return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l,406l, System.currentTimeMillis(), "http://localhost:3606/errors/406"));
    }

    @ExceptionHandler(AcessoNaoAutorizadoException.class)
    public ResponseEntity<DetalhesErro> handlerAcessoNaoAutorizadoException(RecursoNaoEncontradoException e, HttpServletRequest request){
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DetalhesErro(e.getMessage(), 401l,401l, System.currentTimeMillis(), "http://localhost:3606/errors/401"));
    }

}
