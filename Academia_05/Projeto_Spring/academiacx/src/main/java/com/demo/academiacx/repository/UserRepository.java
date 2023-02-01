package com.demo.academiacx.repository;

import com.demo.academiacx.model.ClienteModel;
import com.demo.academiacx.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByNameAndEmail(String name, String email);

    Optional<UserModel> findByUsername(String username);

    Optional<List<UserModel>> findByNameOrEmail(String name, String email);

//Querry Statica
    @Query("SELECT userModel FROM UserModel AS userModel WHERE userModel.id = ?1")
    Optional<UserModel> buscaPorId(Long id);

    @Query("select u from UserModel u where u.id = ?1 or u.name = ?2 or u.email = ?3 or u.username = ?4")
    Optional<UserModel> findByNameOrEmailOrIdOrUsername(Long id, String name, String email, String username);

    @Query("select c from ClienteModel c where c.id = ?1")
    ClienteModel findClienteModelByClienteId(Long clienteId);
}