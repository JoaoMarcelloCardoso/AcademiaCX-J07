package com.commerce.acdemycx.repository;

import com.commerce.acdemycx.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}
