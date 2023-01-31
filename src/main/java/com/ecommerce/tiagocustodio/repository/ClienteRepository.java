package com.ecommerce.tiagocustodio.repository;

import com.ecommerce.tiagocustodio.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}

