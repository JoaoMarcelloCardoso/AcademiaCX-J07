package com.demo.academiacx.model;

import com.demo.academiacx.model.dto.compra.AddItemDto;
import com.demo.academiacx.model.dto.compra.ItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private BigDecimal total;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    @JsonIgnore
    private CarrinhoModel carrinho;

    public ItemModel(ItemDto itemDto) {
        this.id = itemDto.getId();
        this.quantidade = itemDto.getQuantidade();
    }
}
