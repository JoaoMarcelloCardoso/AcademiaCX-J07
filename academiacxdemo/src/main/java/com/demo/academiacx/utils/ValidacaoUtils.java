package com.demo.academiacx.utils;


import com.demo.academiacx.handler.ParametroInvalidoException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ValidacaoUtils {

    static void validarNumerico(String valor, String errorMsg){
        if (valor == null){
            return;
        }

        try {
            new BigInteger(valor);

        } catch (NumberFormatException e){
            throw new ParametroInvalidoException(errorMsg);
        }
    }

    static void validarTamanhoMinino(String valor, int tamanhoMinimo, String errorMsg){
        if (tamanhoMinimo == 0){
            return;
        }

        if (valor == null || valor.length() < tamanhoMinimo){
            throw new ParametroInvalidoException(errorMsg);
        }
    }

    static void validarTamanhoMaximo(String valor, int tamanhoMaximo, String errorMsg){
        if (valor != null && valor.length() > tamanhoMaximo){
            throw new ParametroInvalidoException(errorMsg);
        }
    }

    static void validarTamanhoMinimoMaximo(String valor, int tamanhoMinimo, int tamanhoMaximo, String errorMsg){

        if(valor == null || valor.length() < tamanhoMinimo || valor.length() > tamanhoMaximo){
            throw new ParametroInvalidoException(errorMsg);
        }
    }

//    static void validarNaoVazio(String valor, String msg){
//        if (valor == null || StringUtils.isBlank(valor)){
//            throw new ParametroInvalidoException(msg);
//        }
//    }

    static void validarNaoNulo(Object valor, String msg){
        if (valor == null){
            throw new ParametroInvalidoException(msg);
        }
    }

    static void valorPositivo(BigDecimal valor, String msg){
        if(valor.compareTo(BigDecimal.ZERO) == 1){
            return;
        }

        throw new ParametroInvalidoException(msg);
    }

    static void valorVazio(String valor, String msg){
        if(valor == null || valor.isEmpty()){
            throw new ParametroInvalidoException(msg);
        }
    }

    static void totalPositivo(Double valor, String msg){
        if(valor == null || valor <= 0){
            throw new ParametroInvalidoException(msg);
        }
    }

    static void idNaoNula(Long valor, String msg){
        if(valor == null){
            throw new ParametroInvalidoException(msg);
        }
    }

    static void dataNaoVazia(Date valor, String msg){
        if(valor == null){
            throw new ParametroInvalidoException(msg);
        }
    }

    // REGEX
    static void emailValidation(String email, String msg){

        valorVazio(email, msg);

        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = EMAIL_PATTERN.matcher(email);

        if(!matcher.matches()){
            throw new ParametroInvalidoException(msg);
        }
    }

    static void passwordValidation(String password, String msg){

        valorVazio(password, msg);

        // Senha de 8 a 16 caracteres com pelo menos um dígito, pelo menos um
        // letra minúscula, pelo menos uma letra maiúscula, pelo menos uma
        // caractere especial sem espaços em branco
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

        Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

        Matcher matcher = PASSWORD_PATTERN.matcher(password);

        if(!matcher.matches()){
            throw new ParametroInvalidoException(msg);
        }
    }


    static void cpfValidation(String cpf, String msg){

        valorVazio(cpf, msg);

        String CPF_REGEX = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)";

        Pattern CPF_PATTERN = Pattern.compile(CPF_REGEX);

        Matcher matcher = CPF_PATTERN.matcher(cpf);

        if(!matcher.matches()){
            throw new ParametroInvalidoException(msg);
        }

    }


}

