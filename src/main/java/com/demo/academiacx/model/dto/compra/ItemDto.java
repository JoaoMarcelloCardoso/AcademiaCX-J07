package com.demo.academiacx.model.dto.compra;

import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ItemModel;
import com.demo.academiacx.model.ProdutoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;

    private int quantidade;

    private Long produto_id;

    private Long carrinho_id;

    public ItemDto(ItemModel itemModel) {
        this.id = itemModel.getId();
        this.quantidade = itemModel.getQuantidade();
        this.produto_id = itemModel.getProduto().getId();
        this.carrinho_id = itemModel.getCarrinho().getId();
    }

}
