package com.ecommerce.tiagocustodio.model;

import com.ecommerce.tiagocustodio.model.dto.CarrinhoDto;
import com.ecommerce.tiagocustodio.model.dto.ItemDto;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produtoModel;

    @ManyToOne
    @JoinColumn(name = "preco_id")
    private PrecoModel precoModel;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoModel carrinhoModel;

    public ItemModel() {
    }

    public ItemModel(Long id, int quantidade, BigDecimal total, ProdutoModel produtoModel, PrecoModel precoModel, CarrinhoModel carrinhoModel) {
        this.id = id;
        this.quantidade = quantidade;
        this.total = total;
        this.produtoModel = produtoModel;
        this.precoModel = precoModel;
        this.carrinhoModel = carrinhoModel;
    }

    public ItemModel(ItemDto itemDto) {
        this.id= itemDto.getId();
        this.quantidade = getQuantidade();
        this.total = getTotal();
        this.produtoModel = getProdutoModel();
        this.precoModel = getPrecoModel();
        this.carrinhoModel = getCarrinhoModel();
    }

    public static List<CarrinhoDto> parseList(List<CarrinhoModel> carrinhos) {
        return carrinhos.stream().map(CarrinhoDto::new).collect(Collectors.toList());
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