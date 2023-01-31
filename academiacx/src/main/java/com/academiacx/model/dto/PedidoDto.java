package com.academiacx.model.dto;


import com.academiacx.model.PedidoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PedidoDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate data;
    private String metodoPagamento;
    private EnderecoDto endereco;
    private CarrinhoDto carrinho;


    public PedidoDto() {

    }

    public PedidoDto(PedidoModel pedidoModel) {
        this.id = pedidoModel.getId();
        this.data = pedidoModel.getData();
        this.metodoPagamento = pedidoModel.getMetodoPagamento();
        this.endereco = new EnderecoDto(pedidoModel.getEndereco());
        this.carrinho = new CarrinhoDto(pedidoModel.getCarrinho());
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

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    public CarrinhoDto getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDto carrinho) {
        this.carrinho = carrinho;
    }
}
