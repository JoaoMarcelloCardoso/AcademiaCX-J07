package com.academiacx.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_carrinho")
public class CarrinhoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data_hora;
    private double total;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    public CarrinhoModel() {
    }
    public CarrinhoModel(Long id,LocalDateTime data_hora,ClienteModel cliente){
        this.id=id;
        this.data_hora=data_hora;
        this.cliente=cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatahora() {
        return data_hora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.data_hora = datahora;
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
