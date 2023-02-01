package com.demo.academiacx.model.dto.carrinho;

import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.dto.compra.ItemDto;
import com.demo.academiacx.model.dto.user.ClienteDto;
import com.demo.academiacx.model.dto.produto.ProdutoDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class CarrinhoDto {

    private Long id;
    private BigDecimal total;

    private ClienteDto cliente;

    private ProdutoDto produto;

    private List<ItemDto> cartItems;

    public CarrinhoDto(List<ItemDto> itemCarrinhoDtoList, BigDecimal total) {
        this.cartItems = itemCarrinhoDtoList;
        this.total = total;
    }
    public CarrinhoDto() {

    }

    public CarrinhoDto(CarrinhoModel carrinhoModel) {
        this.id = carrinhoModel.getId();
        this.total = carrinhoModel.getTotal();
        this.cliente = new ClienteDto(carrinhoModel.getCliente());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public ProdutoDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public List<ItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
