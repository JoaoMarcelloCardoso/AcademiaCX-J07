package com.academiacx.repository;

import com.academiacx.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByCpf(String cpf);

    Optional<ClienteModel> findByNome(String nome);
}
