package com.demo.academiacx.repository;

import com.demo.academiacx.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByUsername(String username);

    Optional<ClienteModel> findByCpf(String cpf);


}
