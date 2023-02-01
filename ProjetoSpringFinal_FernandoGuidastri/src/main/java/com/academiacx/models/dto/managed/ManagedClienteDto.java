package com.academiacx.models.dto.managed;

import com.academiacx.models.dto.ClienteDto;

public class ManagedClienteDto extends ClienteDto {

    private String password;


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
