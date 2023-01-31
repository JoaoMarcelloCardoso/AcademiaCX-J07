package com.example.academiacx2.repository;

import com.example.academiacx2.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByUsername(String username);

    Optional<List<ClienteModel>> findByCpfOrNome(String cpf, String nome);
}
