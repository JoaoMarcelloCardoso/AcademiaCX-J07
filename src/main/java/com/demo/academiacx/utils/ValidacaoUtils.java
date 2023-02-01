package com.demo.academiacx.utils;

import com.demo.academiacx.handler.exceptions.ParametroInvalidoException;
import com.demo.academiacx.handler.exceptions.ParametroNullException;
import com.demo.academiacx.handler.exceptions.TamanhoInvalidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface ValidacaoUtils {


    static void validarVazio(String valor, String errorMessage) {
        if (valor == null || valor.isEmpty() || valor.isBlank()) {
            throw new ParametroNullException(errorMessage);
        }
    }

    static void validarRangeTamanho(String valor, int Min, int Max, String errorMessage) {

        validarVazio(valor, errorMessage);

        if (valor.length() < Min || valor.length() > Max) {
            throw new TamanhoInvalidoException(errorMessage);
        }
    }


    static void validarCep(String cep, String errorMessage) {

        validarVazio(cep, errorMessage);

        String Regex = "\\\\d{5}-\\\\d{3}";

        Pattern pattern = Pattern.compile(Regex);

        Matcher matcher = pattern.matcher(cep);

        if (!matcher.matches()){
            throw new ParametroInvalidoException(errorMessage);
        }
    }


    static void validarCpf(String cpf, String errorMessage) {

        validarVazio(cpf, errorMessage);
        String Regex = "/^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$/";

        Pattern pattern = Pattern.compile(Regex);

        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()){
            throw new ParametroInvalidoException(errorMessage);
        }
    }

    static void validarEmail(String email, String errorMessage) {

        validarVazio(email, errorMessage);
        String Regex = "/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/\n";

        Pattern pattern = Pattern.compile(Regex);

        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()){
            throw new ParametroInvalidoException(errorMessage);
        }
    }
}
