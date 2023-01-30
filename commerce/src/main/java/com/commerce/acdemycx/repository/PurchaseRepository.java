package com.commerce.acdemycx.repository;

import com.commerce.acdemycx.model.PurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseModel, Long> {
}
