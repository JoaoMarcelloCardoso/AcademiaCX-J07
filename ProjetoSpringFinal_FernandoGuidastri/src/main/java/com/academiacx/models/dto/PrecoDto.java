package com.academiacx.models.dto;

import com.academiacx.models.PrecoModel;

public class PrecoDto {

    private double valor;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrecoDto() {
    }

    public PrecoDto(double valor) {
        this.valor = valor;
    }
    public PrecoDto(PrecoModel precoModel){
        this.valor= precoModel.getValor();
        this.id= precoModel.getId();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
