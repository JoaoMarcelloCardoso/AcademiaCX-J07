package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.ProdutoDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sku;
    private String nome;

    public ProdutoModel() {
    }

    public ProdutoModel(ProdutoDto produtoDto){

        this.id = produtoDto.getId();
        this.sku = produtoDto.getSku();
        this.nome = produtoDto.getNome();

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
}
