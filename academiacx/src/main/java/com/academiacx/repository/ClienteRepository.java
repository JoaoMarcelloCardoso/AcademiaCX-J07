package com.academiacx.repository;

import com.academiacx.model.ClienteModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByUsername(String username);

    Optional<List<ClienteModel>> findByCpfOrEmailOrTelefone(String cpf, String email, String telefone);


    @Query("SELECT cliente FROM ClienteModel as cliente " +
            "JOIN CarrinhoModel as carrinho ON carrinho.cliente = cliente " +
            "JOIN ItemModel as item ON item.carrinho = carrinho WHERE cliente.id = ?1")
    Optional<List<ClienteModel>> validarItens(Long id);


    @Modifying
    @Transactional
    @Query("DELETE FROM EnderecoModel as endereco WHERE endereco.cliente.id = ?1")
    void limparEnderecos(Long id);

}
