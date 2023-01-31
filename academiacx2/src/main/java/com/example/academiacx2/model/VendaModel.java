package com.example.academiacx2.model;

import com.example.academiacx2.model.dto.VendaDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_venda")
public class VendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel clienteModel;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoModel enderecoModel;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private ItemModel itemModel;

    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private CarrinhoModel carrinhoModel;

    public VendaModel() {
    }

    public VendaModel(VendaDto vendaDto) {
        this.id = vendaDto.getId();
        this.clienteModel = vendaDto.getClienteModel();
        this.enderecoModel = vendaDto.getEnderecoModel();
        this.itemModel = vendaDto.getItemModel();
        this.carrinhoModel = vendaDto.getCarrinhoModel();
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
