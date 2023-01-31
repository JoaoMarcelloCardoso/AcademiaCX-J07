package com.example.academiacx2.model;

import com.example.academiacx2.model.dto.ItemDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProdutoModel produtoModel;
    @ManyToOne
    @JoinColumn(name = "id_preco")
    private PrecoModel precoModel;

    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private CarrinhoModel carrinhoModel;

    public ItemModel() {
    }

    public ItemModel(ItemDto itemDto) {
        this.id = itemDto.getId();
        this.quantidade = itemDto.getQuantidade();
        this.total = itemDto.getTotal();
        this.produtoModel = itemDto.getProdutoModel();
        this.precoModel = itemDto.getPrecoModel();
        this.carrinhoModel = itemDto.getCarrinhoModel();
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