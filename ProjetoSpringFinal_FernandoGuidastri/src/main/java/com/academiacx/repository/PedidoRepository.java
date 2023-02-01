package com.academiacx.repository;

import com.academiacx.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel,Long> {
}
