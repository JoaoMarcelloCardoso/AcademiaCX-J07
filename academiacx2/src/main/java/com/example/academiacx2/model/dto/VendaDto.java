package com.example.academiacx2.model.dto;

import com.example.academiacx2.model.*;

public class VendaDto {

    private Long id;
    private ClienteModel clienteModel;
    private EnderecoModel enderecoModel;
    private ItemModel itemModel;
    private CarrinhoModel carrinhoModel;

    public VendaDto() {
    }

    public VendaDto(VendaModel vendaModel) {
        this.id = vendaModel.getId();
        this.clienteModel = vendaModel.getClienteModel();
        this.enderecoModel = vendaModel.getEnderecoModel();
        this.itemModel = vendaModel.getItemModel();
        this.carrinhoModel = vendaModel.getCarrinhoModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public EnderecoModel getEnderecoModel() {
        return enderecoModel;
    }

    public void setEnderecoModel(EnderecoModel enderecoModel) {
        this.enderecoModel = enderecoModel;
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public CarrinhoModel getCarrinhoModel() {
        return carrinhoModel;
    }

    public void setCarrinhoModel(CarrinhoModel carrinhoModel) {
        this.carrinhoModel = carrinhoModel;
    }
}
