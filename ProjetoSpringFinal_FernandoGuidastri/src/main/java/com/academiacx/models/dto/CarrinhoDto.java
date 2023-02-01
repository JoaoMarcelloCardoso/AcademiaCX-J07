package com.academiacx.models.dto;

import com.academiacx.models.CarrinhoModel;

import java.time.LocalDateTime;

public class CarrinhoDto {

    private LocalDateTime data_hora;
    private double total;
    private Long id;
    private ClienteDto clienteDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarrinhoDto(LocalDateTime data_hora, double total) {
        this.data_hora = data_hora;
        this.total = total;
    }

    public CarrinhoDto(CarrinhoModel carrinhoModel){
        this.data_hora=carrinhoModel.getDatahora();
        this.total=carrinhoModel.getTotal();
        this.id= carrinhoModel.getId();
        this.clienteDto=new ClienteDto(carrinhoModel.getCliente());
    }

    public CarrinhoDto(LocalDateTime data_hora, double total, Long id, ClienteDto clienteDto) {
        this.data_hora = data_hora;
        this.total = total;
        this.id = id;
        this.clienteDto = clienteDto;
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }

    public CarrinhoDto() {
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
