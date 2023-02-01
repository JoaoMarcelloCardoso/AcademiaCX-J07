package com.demo.academiacx.repository;

import com.demo.academiacx.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    Optional<List<ItemModel>> findByCarrinhoId(Long id);

    Optional<List<ItemModel>> deleteByCarrinhoId(Long id);

}
