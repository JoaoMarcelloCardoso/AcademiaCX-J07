package br.com.commerce.academiacx.handler.resources;


import br.com.commerce.academiacx.handler.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<DetalhesErro> handlerInternalServerErrorExceptionException(InternalServerErrorException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DetalhesErro(e.getMessage(), 500l, 500l, System.currentTimeMillis(), "http://localhost:8080/erros/500"));
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<DetalhesErro> handlerBadGatewayExceptionException(BadGatewayException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new DetalhesErro(e.getMessage(), 502l, 502l, System.currentTimeMillis(), "http://localhost:8080/erros/502"));
    }
}