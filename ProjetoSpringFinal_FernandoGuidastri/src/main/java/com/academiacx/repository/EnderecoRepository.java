package com.academiacx.repository;

import com.academiacx.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
    Optional<List<EnderecoModel>> findEnderecoModelsByClienteId(Long id);
}
