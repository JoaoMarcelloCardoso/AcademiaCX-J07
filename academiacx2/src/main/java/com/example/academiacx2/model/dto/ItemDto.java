package com.example.academiacx2.model.dto;

import com.example.academiacx2.model.CarrinhoModel;
import com.example.academiacx2.model.ItemModel;
import com.example.academiacx2.model.PrecoModel;
import com.example.academiacx2.model.ProdutoModel;

public class ItemDto {

    private Long id;
    private Integer quantidade;
    private Double total;
    private ProdutoModel produtoModel;
    private PrecoModel precoModel;
    private CarrinhoModel carrinhoModel;

    public ItemDto() {
    }

    public ItemDto(ItemModel itemModel) {
        this.id = itemModel.getId();
        this.quantidade = itemModel.getQuantidade();
        this.total = itemModel.getTotal();
        this.produtoModel = itemModel.getProdutoModel();
        this.precoModel = itemModel.getPrecoModel();
        this.carrinhoModel = itemModel.getCarrinhoModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ProdutoModel getProdutoModel() {
        return produtoModel;
    }

    public void setProdutoModel(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }

    public PrecoModel getPrecoModel() {
        return precoModel;
    }

    public void setPrecoModel(PrecoModel precoModel) {
        this.precoModel = precoModel;
    }

    public CarrinhoModel getCarrinhoModel() {
        return carrinhoModel;
    }

    public void setCarrinhoModel(CarrinhoModel carrinhoModel) {
        this.carrinhoModel = carrinhoModel;
    }
}
