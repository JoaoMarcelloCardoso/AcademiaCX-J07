package br.com.commerce.academiacx.utils;

import br.com.commerce.academiacx.handler.exceptions.ParametroInvalidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ValidacaoUtils {

    static void validarTamanhoMinimo(String valor, int tamanhoMinimo, String errormsgs) {
        if (tamanhoMinimo == 0) {
            return;
        }
        if (valor == null || valor.length() < tamanhoMinimo) {
            throw new ParametroInvalidoException(errormsgs);
        }
    }

    static void PasswordValidator(String password, String msg) {

        // minimo de 4 caracteres, incluir ao menos uma letra maiuscula, minuscula  e um digito

        String PASSWORD_REGEX =
                "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

        Matcher matcher = PASSWORD_PATTERN.matcher(password);

        if (!matcher.matches()) {
            throw new ParametroInvalidoException(msg);
        }

    }

    static void EmailValidator(String email, String msg) {
        String EMAIL_REGEX =
                "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = EMAIL_PATTERN.matcher(email);

        if (!matcher.matches()) {
            throw new ParametroInvalidoException(msg);
        }
    }

    static void cpfFormater(String cpf, String msg) {
        String CPF_REGEX =
                "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)";

        Pattern CPF_PATTERN = Pattern.compile(CPF_REGEX);

        Matcher matcher = CPF_PATTERN.matcher(cpf);

        if (!matcher.matches()) {
            throw new ParametroInvalidoException(msg);
        }
    }
}
