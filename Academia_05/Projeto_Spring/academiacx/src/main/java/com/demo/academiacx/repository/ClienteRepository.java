package com.demo.academiacx.repository;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {


    ClienteModel findByNome(String name);

    @Query("select u from ClienteModel u where u.id = ?1 or u.nome = ?2")
    Optional<ClienteModel> findByNameOrId(Long id, String name);

}
