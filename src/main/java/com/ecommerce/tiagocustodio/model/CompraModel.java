package com.ecommerce.tiagocustodio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "compra")
public class CompraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    @OneToMany
    @JoinColumn(name = "carrinho_id")
    private List<CarrinhoModel> carrinhos;

    public CompraModel() {}

    public CompraModel(Long id, ClienteModel clienteModel, List<CarrinhoModel> carrinhos) {
        this.id = id;
        this.clienteModel = clienteModel;
        this.carrinhos = carrinhos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public List<CarrinhoModel> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(List<CarrinhoModel> carrinhos) {
        this.carrinhos = carrinhos;
    }
}
