package com.demo.academiacx.repository;

import com.demo.academiacx.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long> {

    @Query("select c from CompraModel c where c.cliente.id = ?1 or c.carrinho.id = ?2 or c.endereco.id = ?3")
    List<CompraModel> findByCarrinhoOrClienteOrEndereco(Long id, Long id1, Long id2);


    //Optional<List<CompraModel>> findByCarrinhoOrClienteOrEnderecoOrItem(Long id_carrinho, Long id_cliente, Long id_endereco, Long id_item);

}
