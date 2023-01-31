package com.academiacx.model.dto;

import com.academiacx.model.ItemModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class ItemDto {

    private Long id;
    private int quantidade;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal total;
    private ProdutoDto produto;
    private CarrinhoDto carrinho;


    public ItemDto() {

    }

    public ItemDto(ItemModel itemModel) {
        this.id = itemModel.getId();
        this.quantidade = itemModel.getQuantidade();
        this.total = itemModel.getTotal();
        this.produto = new ProdutoDto(itemModel.getProduto());
        this.carrinho = new CarrinhoDto(itemModel.getCarrinho());
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

    public ProdutoDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public CarrinhoDto getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDto carrinho) {
        this.carrinho = carrinho;
    }
}
