package com.ecommerce.tiagocustodio.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carrinho")
public class CarrinhoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "produto_id")
    private List<CarrinhoModel> produtos;

    public CarrinhoModel() {}

    public CarrinhoModel(Long id, String name, List<CarrinhoModel> produtos) {
        this.id = id;
        this.name = name;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarrinhoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CarrinhoModel> produtos) {
        this.produtos = produtos;
    }
}
