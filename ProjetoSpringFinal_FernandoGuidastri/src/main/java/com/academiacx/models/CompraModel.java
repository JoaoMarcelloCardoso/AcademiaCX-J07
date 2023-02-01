package com.academiacx.models;

import com.academiacx.models.dto.ClienteDto;
import com.academiacx.models.dto.EnderecoDto;
import com.academiacx.models.dto.ItemDto;

import java.util.List;
public class CompraModel {
    private List<ItemDto> itens;
    private EnderecoDto enderecoModel;
    private ClienteDto clienteDto;
    private String mensagem;

    public CompraModel() {
    }

    public CompraModel(List<ItemDto> itens, EnderecoDto enderecoModel, ClienteDto clienteDto) {
        this.itens = itens;
        this.enderecoModel = enderecoModel;
        this.clienteDto = clienteDto;
    }

    public String getMensagem(){
        return mensagem;
    }
    public void setMensagem(String mensagem){
        this.mensagem=mensagem;
    }

    public List<ItemDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemDto> itens) {
        this.itens = itens;
    }

    public EnderecoDto getEnderecoModel() {
        return enderecoModel;
    }

    public void setEnderecoModel(EnderecoDto enderecoModel) {
        this.enderecoModel = enderecoModel;
    }

    public ClienteDto getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDto clienteDto) {
        this.clienteDto = clienteDto;
    }
}
