package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import com.ecommerce.tiagocustodio.model.ClienteModel;
import com.ecommerce.tiagocustodio.model.CompraModel;

import java.util.List;

public class CompraDto {

    private Long id;
    private String metodo_pagamento;
    private Long carrinhoId;

    public CompraDto() {
    }

    public CompraDto(CompraModel compraModel) {
        this.id = compraModel.getId();
        this.metodo_pagamento = compraModel.getMetodo_pagamento();
        this.carrinhoId = compraModel.getCarrinhoModel().getId();
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

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }
}
