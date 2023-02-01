package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.ItemDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private Double total;

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

    public ItemModel(ItemDto itemDto){

        this.id = itemDto.getId();
        this.quantidade = itemDto.getQuantidade();
        this.total = itemDto.getTotal();
        this.produto = itemDto.getProduto();
        this.preco = itemDto.getPreco();
        this.carrinho = itemDto.getCarrinho();

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
