package com.ecommerce.tiagocustodio.model.dto;

import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CarrinhoDto {
    private Long id;
    private Date dataHora;
    private BigDecimal total;
    private Long clienteId;
    private List<ItemDto> itens;

    public CarrinhoDto() {
    }

    public CarrinhoDto(CarrinhoModel carrinhoModel) {
        this.id = carrinhoModel.getId();
        this.dataHora = carrinhoModel.getDataHora();
        this.total = carrinhoModel.getTotal();
        this.clienteId = carrinhoModel.getClienteModel().getId();
        this.itens = ItemDto.parseList(carrinhoModel.getItens());
    }

    public static List<CarrinhoDto> parseList(List<CarrinhoModel> carrinhos) {
        return carrinhos.stream().map(CarrinhoDto::new).collect(Collectors.toList());
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

    public List<ItemDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemDto> itens) {
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}