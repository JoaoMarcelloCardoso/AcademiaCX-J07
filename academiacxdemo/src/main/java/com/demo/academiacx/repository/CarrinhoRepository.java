package com.demo.academiacx.repository;

import com.demo.academiacx.model.CarrinhoModel;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {

    Optional<List<CarrinhoModel>> findByClienteId(Long id);

}
