package com.demo.academiacx.repository;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    List<EnderecoModel> findByClienteId(Long id);

    @Query("select c from ClienteModel c where c.id = ?1")
    ClienteModel findClienteModelByClienteId(Long id);
}
