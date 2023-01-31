package com.academiacx.repository;

import com.academiacx.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    Optional<List<ProdutoModel>> findByNomeOrSku(String nome, String sku);


}
