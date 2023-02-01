package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.carrinho.CarrinhoDto;
import com.demo.academiacx.model.dto.produto.ProdutoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "tb_carrinho")
@Getter
@Setter
public class CarrinhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    private Integer quantidade;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemModel> items;

    public CarrinhoModel() {

    }

    public CarrinhoModel(CarrinhoDto carrinhoDto) {
        this.id = carrinhoDto.getId();
        this.total = carrinhoDto.getTotal();
        this.cliente = new ClienteModel(carrinhoDto.getCliente());
    }

    public CarrinhoModel(CarrinhoDto carrinhoDto, Integer quantidade, ProdutoDto produtoDto) {
        this.id = carrinhoDto.getId();
        this.total = carrinhoDto.getTotal();
        this.quantidade = quantidade;
        this.cliente = new ClienteModel(carrinhoDto.getCliente());
    }
}
