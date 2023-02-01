package com.demo.academiacx.repository;

import com.demo.academiacx.model.CarrinhoModel;
import com.demo.academiacx.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {
    @Query("SELECT C FROM CarrinhoModel c where c.cliente.id =?1")
    static CarrinhoModel findCarrinhoModelByClienteId(Long id) {
        return null;
    }


    @Transactional
    @Modifying

    @Query("delete from CarrinhoModel c where c.cliente.id = ?1")
    void deleteByClienteId(Long id);



    static List<ProdutoModel> findAllProductModelByCarrinhoId(Long id) {
        return null;
    }

    Optional<CarrinhoModel> findByClienteId(Long id);
}
