package com.ecommerce.tiagocustodio.model;

import com.ecommerce.tiagocustodio.model.dto.CompraDto;
import com.ecommerce.tiagocustodio.model.dto.ItemDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "compra")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodo_pagamento;

    @ManyToOne
    @JoinColumn(nullable = true, name = "carrinho_id")
    private CarrinhoModel carrinhoModel;

    public CompraModel() {
    }

    public CompraModel(Long id, String metodo_pagamento, CarrinhoModel carrinhoModel) {
        this.id = id;
        this.metodo_pagamento = metodo_pagamento;
        this.carrinhoModel = carrinhoModel;
    }

    public CompraModel(CompraDto compraDto) {
        this.id= compraDto.getId();
        this.metodo_pagamento = getMetodo_pagamento();
        this.carrinhoModel = getCarrinhoModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }


    public CarrinhoModel getCarrinhoModel() {
        return carrinhoModel;
    }

    public void setCarrinhoModel(CarrinhoModel carrinhoModel) {
        this.carrinhoModel = carrinhoModel;
    }
}
