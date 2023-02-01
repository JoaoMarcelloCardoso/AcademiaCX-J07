package com.demo.academiacx.model.dto.user;

import com.demo.academiacx.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String email;
    private Long cliente_id;
    private String username;

    public UserResponseDto() {
    }

    public UserResponseDto(UserModel userModel) {
        this.email = userModel.getEmail();
        this.cliente_id = userModel.getCliente().getId();
        this.username = userModel.getUsername();
    }
}
