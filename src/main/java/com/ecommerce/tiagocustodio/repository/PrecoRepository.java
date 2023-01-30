package com.ecommerce.tiagocustodio.repository;

import com.ecommerce.tiagocustodio.model.PrecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecoRepository extends JpaRepository<PrecoModel, Long> {

}