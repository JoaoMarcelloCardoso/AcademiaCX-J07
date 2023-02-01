package com.academiacx.repository;

import com.academiacx.models.CarrinhoModel;
import com.academiacx.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {
    Optional<List<CarrinhoModel>> findCarrinhoModelsByClienteId(Long id);
}
