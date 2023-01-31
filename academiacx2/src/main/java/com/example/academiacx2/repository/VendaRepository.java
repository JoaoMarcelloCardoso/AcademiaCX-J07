package com.example.academiacx2.repository;

import com.example.academiacx2.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<VendaModel, Long> {

    Optional<List<VendaModel>> findByCarrinhoModelOrClienteModelOrEnderecoModelOrItemModel(Long id_carrinho, Long id_cliente, Long id_endereco, Long id_item);
}
