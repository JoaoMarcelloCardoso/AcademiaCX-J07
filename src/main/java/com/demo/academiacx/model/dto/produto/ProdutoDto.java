package com.demo.academiacx.model.dto.produto;

import com.demo.academiacx.model.ProdutoModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoDto {

    private Long id;
    private String sku;
    private String nome;

    private Double preco;

    public ProdutoDto() {

    }

    public ProdutoDto(ProdutoModel produtoModel) {
        this.id = produtoModel.getId();
        this.nome = produtoModel.getNome();
        this.sku = produtoModel.getSku();
        this.preco = produtoModel.getPreco().doubleValue();
    }
}
