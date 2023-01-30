package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.ItemModel;
import java.util.List;
import java.util.stream.Collectors;

public class ItemDto {
    private Long id;

    private int quantidade;

    private ProdutoDto produto;

    private Long carrinhoId;

    public ItemDto() {
    }

    public ItemDto(ItemModel itemModel) {
        this.id = itemModel.getId();
        this.quantidade = itemModel.getQuantidade();
        this.produto = new ProdutoDto(itemModel.getProdutoModel());
        this.carrinhoId = itemModel.getCarrinhoModel().getId();
    }

    public static List<ItemDto> parseList(List<ItemModel> itens) {
        return itens.stream().map(ItemDto::new).collect(Collectors.toList());
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

    public ProdutoDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }
}
