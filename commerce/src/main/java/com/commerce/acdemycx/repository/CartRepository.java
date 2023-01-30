package com.commerce.acdemycx.repository;

import com.commerce.acdemycx.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel, Long> {
}
