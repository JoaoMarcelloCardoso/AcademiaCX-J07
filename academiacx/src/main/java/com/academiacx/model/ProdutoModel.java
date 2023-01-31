package com.academiacx.model;

import com.academiacx.model.dto.ProdutoDto;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sku;
    private String nome;
    private BigDecimal preco;


    public ProdutoModel() {

    }

    public ProdutoModel(Long id) {
        this.id = id;
    }

    public ProdutoModel(ProdutoDto produtoDto) {
        this.id = produtoDto.getId();
        this.nome = produtoDto.getNome();
        this.sku = produtoDto.getSku();
        this.preco = produtoDto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
