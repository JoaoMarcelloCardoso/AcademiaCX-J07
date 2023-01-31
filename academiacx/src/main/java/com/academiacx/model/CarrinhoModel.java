package com.academiacx.model;

import com.academiacx.model.dto.CarrinhoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "tb_carrinho")
public class CarrinhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate data;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal total;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    boolean ativo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;


    public CarrinhoModel() {

    }

    public CarrinhoModel(Long id) {
        this.id = id;
    }

    public CarrinhoModel(CarrinhoDto carrinhoDto) {
        this.id = carrinhoDto.getId();
        this.total = carrinhoDto.getTotal();
        this.data = carrinhoDto.getData();
        this.cliente = new ClienteModel(carrinhoDto.getCliente());
        this.ativo = carrinhoDto.isAtivo();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
