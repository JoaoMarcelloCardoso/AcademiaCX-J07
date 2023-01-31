package com.example.academiacx2.repository;

import com.example.academiacx2.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    Optional<List<ProdutoModel>> findBySkuOrNome(String sku, String nome);
}
