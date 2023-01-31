package com.academiacx.repository;

import com.academiacx.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    Optional<List<EnderecoModel>> findAllByClienteId(Long clienteId);

    Optional<List<EnderecoModel>> findByBairroOrCidadeOrUf(String bairro, String cidade, String uf);
}
