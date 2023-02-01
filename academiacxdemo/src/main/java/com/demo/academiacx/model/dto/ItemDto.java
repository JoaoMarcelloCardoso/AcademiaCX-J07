package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.PrecoModel;
import com.demo.academiacx.model.ProdutoModel;

public class ItemDto {

    private Long id;
    private int quantidade;
    private Double total;
    private ProdutoModel produto;
    private PrecoModel preco;
    private CarrinhoModel carrinho;


    public ItemDto() {
    }

    public ItemDto(ItemModel itemModel){

        this.id = itemModel.getId();
        this.quantidade = itemModel.getQuantidade();
        this.total = itemModel.getTotal();
        this.produto = itemModel.getProduto();
        this.preco = itemModel.getPreco();
        this.carrinho = itemModel.getCarrinho();

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public PrecoModel getPreco() {
        return preco;
    }

    public void setPreco(PrecoModel preco) {
        this.preco = preco;
    }

    public CarrinhoModel getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoModel carrinho) {
        this.carrinho = carrinho;
    }
}
