package com.academiacx.utils;

import com.academiacx.handler.exceptions.SenhaInvalidaException;

import java.util.regex.Pattern;

public interface InterfaceUtils {

    static boolean validateName(String name) {
        return name.matches("^[a-zA-Z\\s]{5,30}$") && name.length() >= 5 && name.length() <= 30;
    }

    static void validatePassword(String password) throws SenhaInvalidaException {
        Pattern pattern = Pattern.compile("(?-i)(?=^.{8,}$)((?!.*\\s)(?=.*[A-Z])(?=.*[a-z]))(?=(1)(?=.*\\d)|.*[^A-Za-z0-9])^.*$");
        if(!pattern.matcher(password).matches()){
            throw new SenhaInvalidaException("Senha Invalida");
        }
    }

    static boolean validateCPF(String cpf) {
        return cpf.matches("\\d{11}");
    }
}
