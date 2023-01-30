package com.ecommerce.tiagocustodio.repository;

import com.ecommerce.tiagocustodio.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    List<ItemModel> findByCarrinhoModel(Long carrinhoModel);
}