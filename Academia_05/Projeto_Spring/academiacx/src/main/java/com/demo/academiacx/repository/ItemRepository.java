package com.demo.academiacx.repository;

import com.demo.academiacx.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    //Optional<ItemModel> findByCarrinhoId(Long id);

    //void deleteByCarrinhoId(Long id);
}
