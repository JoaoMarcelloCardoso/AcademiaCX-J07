package com.example.academiacx2.model;

import com.example.academiacx2.model.dto.CarrinhoDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_carrinho")
public class CarrinhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime datahora;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel clienteModel;

    public CarrinhoModel() {
    }

    public CarrinhoModel(CarrinhoDto carrinhoDto) {
        this.id = carrinhoDto.getId();
        this.datahora = carrinhoDto.getDatahora();
        this.total = carrinhoDto.getTotal();
        this.clienteModel = carrinhoDto.getClienteModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }
}
