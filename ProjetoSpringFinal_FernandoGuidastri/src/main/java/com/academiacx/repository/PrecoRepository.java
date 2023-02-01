package com.academiacx.repository;

import com.academiacx.models.PrecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrecoRepository extends JpaRepository<PrecoModel, Long> {
    Optional<List<PrecoModel>> findPrecoModelsByProdutoId(Long id);
}
