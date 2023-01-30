package com.commerce.acdemycx.utils;

import com.commerce.acdemycx.handler.exceptions.ErroDeFormatoException;
import com.commerce.acdemycx.handler.exceptions.ParametroInvalidoException;

import java.util.regex.Pattern;

public interface ValidationUtils {


    static void validateNotNullOrEmpty(String teste, String warning){
        if (teste.isBlank() || teste == null || teste.isEmpty()){
            throw new ParametroInvalidoException(warning + " não deve estar vazio");
        }
    }
    static void validateName(String name) {
        if (!name.matches("^[a-zA-Z\\s]{5,30}$") && name.length() >= 5 && name.length() <= 30){
            throw new ErroDeFormatoException("O nome esta invalido");
        }
    }

    static void validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S{6,}).*$");
        if(!pattern.matcher(password).matches()){
            throw new ErroDeFormatoException
                   ("PDSDSassword inválido, o password deve ter pelo menos" +
                         " 6 caracteres, " +
                           "uma letra maiuscula e um caractere especial");
       }
    }

    static void validateCPF(String cpf) {

        if (!cpf.matches("\\d{11}")){
            throw new ErroDeFormatoException("CPF Inválído");
        };
    }

    static void validateCEP(String cep) {
        if (!cep.matches("\\d{8}")) {
            throw new ErroDeFormatoException("CEP Inválido");
        }
    }






}
