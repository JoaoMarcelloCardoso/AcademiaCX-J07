package com.example.academiacx2.repository;

import com.example.academiacx2.model.CarrinhoModel;
import com.example.academiacx2.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    Optional<List<ItemModel>> findByQuantidadeOrTotal(Integer quantidade, Double total);

    Optional<List<ItemModel>> findByCarrinhoModel_Id(Long id);
}
