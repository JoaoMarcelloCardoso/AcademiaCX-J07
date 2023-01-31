package com.academiacx.repository;

import com.academiacx.model.CarrinhoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {

    Optional<List<CarrinhoModel>> findAllByClienteId(Long clienteId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CarrinhoModel as carrinho WHERE carrinho.cliente.id = ?1 AND carrinho.ativo = true")
    void deleteByClienteId(Long clienteId);


    @Modifying
    @Transactional
    @Query("DELETE FROM ItemModel as item WHERE item.carrinho.id = ?1")
    void limparCarrinho(Long carrinhoId);


    @Query("SELECT sum(produto.preco * item.quantidade) FROM ItemModel as item " +
            "JOIN CarrinhoModel carrinho ON item.carrinho = carrinho " +
            "JOIN ProdutoModel produto ON item.produto = produto WHERE carrinho.id = ?1")
    Optional<BigDecimal> calcularTotal(Long id);
}
