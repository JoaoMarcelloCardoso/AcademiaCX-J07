package com.academiacx.model;

import com.academiacx.model.dto.PedidoDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private String metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoModel endereco;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private CarrinhoModel carrinho;


    public PedidoModel() {

    }

    public PedidoModel(PedidoDto pedidoDto) {
        this.id = pedidoDto.getId();
        this.data = pedidoDto.getData();
        this.metodoPagamento = pedidoDto.getMetodoPagamento();
        this.endereco = new EnderecoModel(pedidoDto.getEndereco());
        this.carrinho = new CarrinhoModel(pedidoDto.getCarrinho());
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

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public CarrinhoModel getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoModel carrinho) {
        this.carrinho = carrinho;
    }
}
