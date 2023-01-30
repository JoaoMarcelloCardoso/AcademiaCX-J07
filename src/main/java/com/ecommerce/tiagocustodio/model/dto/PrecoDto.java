package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.PrecoModel;
import java.math.BigDecimal;

public class PrecoDto {
    private Long id;

    private BigDecimal valor;

    private ClienteDto clienteModel;

    public PrecoDto() {
    }

    public PrecoDto(PrecoModel precoModel) {
        this.id = precoModel.getId();
        this.valor = precoModel.getValor();
        this.clienteModel = new ClienteDto(precoModel.getClienteModel());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = BigDecimal.valueOf(valor);
    }

    public ClienteDto getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteDto clienteModel) {
        this.clienteModel = clienteModel;
    }
}