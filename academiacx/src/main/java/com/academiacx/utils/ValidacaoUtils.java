package com.academiacx.utils;

import com.academiacx.handler.exceptions.*;


public interface ValidacaoUtils {


    static void validarId(Long id, String errorMessage) {
        if (id == null || id < 0) {
            throw new IdInvalidoException(errorMessage);
        }
    }


    static void validarVazio(String valor, String errorMessage) {
        if (valor == null || valor.isEmpty() || valor.isBlank()) {
            throw new ParametroNullException(errorMessage);
        }
    }


    static void validarRangeTamanho(String valor, int tamanhoMinimo, int tamanhoMaximo, String errorMessage) {

        validarVazio(valor, errorMessage);

        if (valor.length() < tamanhoMinimo || valor.length() > tamanhoMaximo) {
            throw new TamanhoInvalidoException(errorMessage);
        }
    }


    static void validarCep(String cep, String errorMessage) {

        validarVazio(cep, errorMessage);

        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new ParametroInvalidoException(errorMessage);
        }
    }


    static void validarNumeroEndereco(String numero, String errorMessage) {

        validarVazio(numero, errorMessage);

        if (!numero.matches("[Nn]?\\.?\\s?º?\\s?\\d+")) {
            throw new NumeroInvalidoException(errorMessage);
        }
    }


    static void validarEmail(String email, String errorMessage) {

        validarVazio(email, errorMessage);

        if (!email.matches("[a-zA-Z0-9._%+-]{1,64}@(?:[a-zA-Z0-9-]{1,63}\\.){1,125}[a-zA-Z]{2,63}")) {
            throw new EmailInvalidoException(errorMessage);
        }

    }


    static void validarTelefone(String telefone, String errorMessage) {

        validarVazio(telefone, errorMessage);

        if (!telefone.matches("\\(?\\d{2}\\)?\\s?\\d{5}-?\\d{4}")) {
            throw new TelefoneInvalidoException(errorMessage);
        }
    }


    static void validarCpf(String cpf, String errorMessage) {

        validarVazio(cpf, errorMessage);

        if (!cpf.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")) {
            throw new CpfInvalidoException(errorMessage);
        }

        cpf = cpf.replace(".", "")
                .replace("-", "");


        verificarCpf(cpf, errorMessage);
    }


    private static void verificarCpf(String cpf, String errorMessage) {
        char dig10, dig11;
        int i, num, r, soma = 0, peso = 10;

        for (i = 0; i < 9; i++) {
            num = (int) (cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso -= 1;
        }

        r = 11 - (soma % 11);

        if (r == 10 || r == 11) {
            dig10 = '0';
        } else {
            dig10 = (char) (r + 48);
        }

        soma = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = (int) (cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso -= 1;
        }

        r = 11 - (soma % 11);

        if (r == 10 || r == 11) {
            dig11 = '0';
        } else {
            dig11 = (char) (r + 48);
        }

        if (dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10)) {
            return;
        } else {
            throw new CpfInvalidoException(errorMessage);
        }

    }

}
