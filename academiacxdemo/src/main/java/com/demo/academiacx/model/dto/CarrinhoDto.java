package com.demo.academiacx.model.dto;

import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ClienteModel;
import java.util.Date;

public class CarrinhoDto {

    private Long id;
    private Date datahora;
    private double total;

    private ClienteDto cliente;


    public CarrinhoDto(){

    }

    public CarrinhoDto(CarrinhoModel carrinhoModel) {
        this.id = carrinhoModel.getId();
        this.total = carrinhoModel.getTotal();
        this.datahora = carrinhoModel.getDatahora();
        this.cliente = new ClienteDto(carrinhoModel.getCliente());
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

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }
}
