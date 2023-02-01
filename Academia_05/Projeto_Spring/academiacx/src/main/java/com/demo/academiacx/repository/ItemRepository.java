package com.demo.academiacx.repository;

import com.demo.academiacx.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    @Query("select i from ItemModel i where i.carrinho.id = ?1")
    Optional<ItemModel> findByCarrinhoId(Long id);

    @Query("select i from ItemModel i where i.id = ?1 or i.produto.id = ?2 or i.carrinho.id = ?3")
    Optional<List<ItemModel>> findByProdutoIdOrCarrinhoIdOrItemId(Long id, Long produto_id, Long carrinho_id);



    //void deleteByCarrinhoId(Long id);
}
