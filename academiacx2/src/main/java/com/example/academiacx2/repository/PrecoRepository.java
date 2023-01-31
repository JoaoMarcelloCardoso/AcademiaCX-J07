package com.example.academiacx2.repository;

import com.example.academiacx2.model.CarrinhoModel;
import com.example.academiacx2.model.PrecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrecoRepository extends JpaRepository<PrecoModel, Long> {

    Optional<List<PrecoModel>> findByValorOrId(Double valor, Long id);

    Optional<List<PrecoModel>> findByProdutoModel_Id(Long id);

}
