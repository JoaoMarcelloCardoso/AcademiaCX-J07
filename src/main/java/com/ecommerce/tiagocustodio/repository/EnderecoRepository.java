package com.ecommerce.tiagocustodio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.tiagocustodio.model.EnderecoModel;
@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

}
