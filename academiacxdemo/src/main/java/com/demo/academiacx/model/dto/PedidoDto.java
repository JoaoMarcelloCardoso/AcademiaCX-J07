package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.*;

public class PedidoDto {

    private Long id;
    private ClienteModel cliente;
    private EnderecoModel endereco;
    private ItemModel item;
    private CarrinhoModel carrinho;

    public PedidoDto() {
    }

    public PedidoDto(PedidoModel pedidoModel){

        this.id = pedidoModel.getId();
        this.cliente = pedidoModel.getCliente();
        this.endereco = pedidoModel.getEndereco();
        this.item = pedidoModel.getItem();
        this.carrinho = pedidoModel.getCarrinho();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public CarrinhoModel getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoModel carrinho) {
        this.carrinho = carrinho;
    }
}
