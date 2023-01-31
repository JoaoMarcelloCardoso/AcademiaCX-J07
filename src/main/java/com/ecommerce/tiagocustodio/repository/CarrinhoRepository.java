package com.ecommerce.tiagocustodio.repository;

import com.ecommerce.tiagocustodio.model.CarrinhoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {

}
