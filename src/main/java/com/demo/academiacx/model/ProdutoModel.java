package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.produto.ProdutoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "tb_produto")
@Getter
@Setter
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sku;
    private String nome;

    private BigDecimal preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItemModel> items;


    public ProdutoModel() {

    }

    public ProdutoModel(ProdutoDto produtoDto) {
        this.id = produtoDto.getId();
        this.nome = produtoDto.getNome();
        this.sku = produtoDto.getSku();
        this.preco = new BigDecimal(produtoDto.getPreco());
    }
}
