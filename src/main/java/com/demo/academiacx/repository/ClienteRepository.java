package com.demo.academiacx.repository;

import com.demo.academiacx.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {


    ClienteModel findByNome(String name);


}
