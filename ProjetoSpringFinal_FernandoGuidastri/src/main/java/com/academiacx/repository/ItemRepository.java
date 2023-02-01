package com.academiacx.repository;

import com.academiacx.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    Optional<List<ItemModel>> findItemModelsByCarrinhoId(Long id);
}
