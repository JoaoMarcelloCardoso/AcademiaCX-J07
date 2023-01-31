package com.example.academiacx2.repository;

import com.example.academiacx2.model.CarrinhoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long>  {

    Optional<List<CarrinhoModel>> findByDatahoraOrTotalOrId(LocalDateTime datahora, Double total, Long id);

    Optional<List<CarrinhoModel>> findByClienteModel_Id(Long id);
}
