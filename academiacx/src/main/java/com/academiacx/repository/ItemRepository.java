package com.academiacx.repository;

import com.academiacx.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    Optional<List<ItemModel>> findByCarrinhoId(Long id);

    Optional<Boolean> deleteByCarrinhoId(Long carrinhoId);

    @Query("SELECT sum(produto.preco * item.quantidade) FROM ItemModel as item " +
            "JOIN ProdutoModel produto ON item.produto = produto WHERE item.id = ?1")
    Optional<BigDecimal> calcularTotal(Long id);
}
