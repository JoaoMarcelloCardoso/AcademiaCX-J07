package com.academiacx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private double total;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;
    @ManyToOne
    @JoinColumn(name = "preco_id")
    private PrecoModel preco;
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoModel carrinho;

    public ItemModel() {
    }

    public ItemModel(int quantidade, double total, ProdutoModel produto, PrecoModel preco, CarrinhoModel carrinho) {
        this.quantidade = quantidade;
        this.total = total;
        this.produto = produto;
        this.preco = preco;
        this.carrinho = carrinho;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
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
