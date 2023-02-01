package com.demo.academiacx.repository;

import com.demo.academiacx.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

//    List<PedidoModel> findHistory(Long , Long , Long );
}
