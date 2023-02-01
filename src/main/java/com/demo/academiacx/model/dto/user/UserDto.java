package com.demo.academiacx.model.dto.user;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String nome;
    private String email;
    private String cpf;
    private Long cliente_id;
    private String username;
    private String password;

    public UserDto() {
    }

    public UserDto(UserModel userModel) {
        this.email = userModel.getEmail();
        this.nome = userModel.getName();
        this.cliente_id = userModel.getCliente().getId();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.cpf = userModel.getCliente().getCpf();
    }
}
