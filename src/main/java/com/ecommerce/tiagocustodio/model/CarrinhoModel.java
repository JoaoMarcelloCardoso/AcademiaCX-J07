package com.ecommerce.tiagocustodio.model;

import com.ecommerce.tiagocustodio.model.dto.CarrinhoDto;
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
    private Date dataHora;
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    @OneToMany(mappedBy = "carrinhoModel")
    private List<ItemModel> itens;

    public CarrinhoModel() {
    }

    public CarrinhoModel(Long id, Date dataHora, BigDecimal total, ClienteModel clienteModel, List<ItemModel> itens) {
        this.id = id;
        this.dataHora = dataHora;
        this.total = total;
        this.clienteModel = clienteModel;
        this.itens = itens;
    }

    public CarrinhoModel(CarrinhoDto carrinhoDto) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public List<ItemModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemModel> itens) {
        this.itens = itens;
    }
}