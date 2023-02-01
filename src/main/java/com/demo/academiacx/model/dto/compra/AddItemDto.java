package com.demo.academiacx.model.dto.compra;

import java.math.BigDecimal;

public class AddItemDto {

    private Integer quantidade;
    private BigDecimal price;
    private Long productId;
    private Long userId;

    private Long carrinho_id;

    public AddItemDto() {}

    public AddItemDto(Integer quantidade, BigDecimal price, Long productId, Long userId, Long carrinhoId) {
        this.quantidade = quantidade;
        this.price = price;
        this.productId = productId;
        this.userId = userId;
        this.carrinho_id = carrinhoId;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getCarrinho_id() {
        return carrinho_id;
    }

    public void setCarrinho_id(Long carrinho_id) {
        this.carrinho_id = carrinho_id;
    }


    public Integer getQuantidade() {
        return quantidade;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice(){return price;}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long id) {
        this.productId = id;
    }

}
