package com.example.academiacx2.utils;

import com.example.academiacx2.handler.exceptions.ParametroInvalidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validation {

    static void validarNull(String valor, String msg){
        if(valor == null || valor.isEmpty()){
            throw new ParametroInvalidoException(msg);
        }
    }

    //Validar o CPF
    static void cpfValidation(String cpf, String msg){

       validarNull(cpf, msg);

       String CPF_REGEX = ("^(d{3}.d{3}.d{3}-d{2})|(d{11})$");

       Pattern CPF_PATTERN = Pattern.compile(CPF_REGEX);

       Matcher matcher = CPF_PATTERN.matcher(cpf);

       if(!matcher.matches()){
           throw new ParametroInvalidoException(msg);
       }
    }

    //Validar Estado
    static void ufValidation(String uf, String msg){

        validarNull(uf, msg);

        String UF_REGEX = ("^(?i)(\\s*(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)?)$");

        Pattern UF_PATTERN = Pattern.compile(UF_REGEX);

        Matcher matcher = UF_PATTERN.matcher(uf);

        if(!matcher.matches()){
            throw new ParametroInvalidoException(msg);
        }
    }
}
