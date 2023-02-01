package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.PedidoDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private EnderecoModel endereco;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemModel item;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoModel carrinho;

    public PedidoModel() {

    }

    public PedidoModel(PedidoDto pedidoDto){

        this.id = pedidoDto.getId();
        this.cliente = pedidoDto.getCliente();
        this.endereco = pedidoDto.getEndereco();
        this.item = pedidoDto.getItem();
        this.carrinho = pedidoDto.getCarrinho();

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
