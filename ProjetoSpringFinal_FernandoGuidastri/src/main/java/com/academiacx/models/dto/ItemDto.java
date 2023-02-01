package com.academiacx.models.dto;

import com.academiacx.models.CarrinhoModel;
import com.academiacx.models.ItemModel;
import com.academiacx.models.PrecoModel;
import com.academiacx.models.ProdutoModel;

public class ItemDto {

    private int quantidade;
    private double total;

    private Long id;

    private PrecoDto precoDto;
    private ProdutoDto produtoDto;
    private CarrinhoDto carrinhoDto;

    public PrecoDto getPrecoDto() {
        return precoDto;
    }

    public void setPrecoDto(PrecoDto precoDto) {
        this.precoDto = precoDto;
    }

    public ProdutoDto getProdutoDto() {
        return produtoDto;
    }

    public void setProdutoDto(ProdutoDto produtoDto) {
        this.produtoDto = produtoDto;
    }

    public CarrinhoDto getCarrinhoDto() {
        return carrinhoDto;
    }

    public void setCarrinhoDto(CarrinhoDto carrinhoDto) {
        this.carrinhoDto = carrinhoDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemDto(int quantidade, double total) {
        this.quantidade = quantidade;
        this.total = total;
    }
    public ItemDto(ItemModel itemModel){
        this.id=itemModel.getId();
        this.total= itemModel.getTotal();
        this.quantidade= itemModel.getQuantidade();
        this.produtoDto=new ProdutoDto(itemModel.getProduto());
        this.carrinhoDto=new CarrinhoDto(itemModel.getCarrinho());
        this.precoDto=new PrecoDto(itemModel.getPreco());
    }

    public ItemDto() {
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
