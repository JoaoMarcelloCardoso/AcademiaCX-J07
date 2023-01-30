package com.commerce.acdemycx.model.dto.request;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
public class ClientDtoRequest {

    private String cpf;
    private String nome;
    private String username;
    private String password;

    public ClientDtoRequest(String cpf, String nome, String username, String password) {
        this.cpf = cpf;
        this.nome = nome;
        this.username = username;
        this.password = password;
    }

    public String getCpf() {
        return cpf.replaceAll("[^0-9]", "");
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }

    public String getPasswordDecode(){
        return password;
    };


    public void setPassword(String password) {
        this.password = password;
    }
}
