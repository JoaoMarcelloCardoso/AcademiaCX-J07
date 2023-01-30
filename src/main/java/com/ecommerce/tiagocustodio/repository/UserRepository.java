package com.ecommerce.tiagocustodio.repository;

import com.ecommerce.tiagocustodio.model.UserModel;
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


}
