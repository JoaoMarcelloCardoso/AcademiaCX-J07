package com.demo.academiacx.repository;

import com.demo.academiacx.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    Optional<List<EnderecoModel>> findByClienteId(Long clienteId);
}
