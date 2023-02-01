package com.demo.academiacx.model;


import com.demo.academiacx.model.dto.CarrinhoDto;
import com.demo.academiacx.model.dto.ClienteDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_carrinho")

public class CarrinhoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datahora;
    private double total;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;


    public CarrinhoModel() {
    }


    public CarrinhoModel(Long id) {
        this.id = id;
    }

    public CarrinhoModel(CarrinhoDto carrinhoDto) {
        this.id = carrinhoDto.getId();
        this.total = carrinhoDto.getTotal();
        this.datahora = carrinhoDto.getDatahora();
        this.cliente = new ClienteModel(carrinhoDto.getCliente());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}

