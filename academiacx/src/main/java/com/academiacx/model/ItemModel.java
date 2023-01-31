package com.academiacx.model;

import com.academiacx.model.dto.ItemDto;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private CarrinhoModel carrinho;


    public ItemModel() {

    }

    public ItemModel(ItemDto itemDto) {
        this.id = itemDto.getId();
        this.quantidade = itemDto.getQuantidade();
        this.total = itemDto.getTotal();
        this.produto = new ProdutoModel(itemDto.getProduto());
        this.carrinho = new CarrinhoModel(itemDto.getCarrinho());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public CarrinhoModel getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoModel carrinho) {
        this.carrinho = carrinho;
    }
}
