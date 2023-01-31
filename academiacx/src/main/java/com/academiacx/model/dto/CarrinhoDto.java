package com.academiacx.model.dto;

import com.academiacx.model.CarrinhoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CarrinhoDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate data;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal total;

    private boolean ativo;

    private ClienteDto cliente;




    public CarrinhoDto() {

    }

    public CarrinhoDto(CarrinhoModel carrinhoModel) {
        this.id = carrinhoModel.getId();
        this.total = carrinhoModel.getTotal();
        this.data = carrinhoModel.getData();
        this.cliente = new ClienteDto(carrinhoModel.getCliente());
        this.ativo = carrinhoModel.isAtivo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
