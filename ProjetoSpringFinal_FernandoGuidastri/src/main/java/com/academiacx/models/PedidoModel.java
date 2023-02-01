package com.academiacx.models;

import jakarta.persistence.*;
import org.springframework.security.core.parameters.P;

@Entity
@Table(name="tb_pedido")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private EnderecoModel enderecoModel;

    @OneToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoModel carrinhoModel;

    @Enumerated(EnumType.STRING)
    private PagamentoModel pagamentoModel;

    public PedidoModel() {
    }

    public PedidoModel(EnderecoModel enderecoModel, CarrinhoModel carrinhoModel, PagamentoModel pagamentoModel) {
        this.enderecoModel = enderecoModel;
        this.carrinhoModel = carrinhoModel;
        this.pagamentoModel = pagamentoModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnderecoModel getEnderecoModel() {
        return enderecoModel;
    }

    public void setEnderecoModel(EnderecoModel enderecoModel) {
        this.enderecoModel = enderecoModel;
    }

    public CarrinhoModel getCarrinhoModel() {
        return carrinhoModel;
    }

    public void setCarrinhoModel(CarrinhoModel carrinhoModel) {
        this.carrinhoModel = carrinhoModel;
    }

    public PagamentoModel getPagamentoModel() {
        return pagamentoModel;
    }

    public void setPagamentoModel(PagamentoModel pagamentoModel) {
        this.pagamentoModel = pagamentoModel;
    }
}
