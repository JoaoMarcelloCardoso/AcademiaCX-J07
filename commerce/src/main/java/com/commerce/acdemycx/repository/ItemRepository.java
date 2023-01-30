package com.commerce.acdemycx.repository;

import com.commerce.acdemycx.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

}
