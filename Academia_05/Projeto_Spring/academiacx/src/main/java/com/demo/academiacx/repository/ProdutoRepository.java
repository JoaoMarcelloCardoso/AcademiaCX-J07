package com.demo.academiacx.repository;

import com.demo.academiacx.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    @Query("select p from ProdutoModel p where p.nome = ?1 or p.sku = ?2 or p.id = ?3")
    Optional<ProdutoModel> findByNomeOrSkuOrId(String nome, String sku, Long id);

}
