package com.example.academiacx2.repository;

import com.example.academiacx2.model.CarrinhoModel;
import com.example.academiacx2.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    Optional<List<EnderecoModel>> findByCepOrLogradouroOrNumeroOrBairroOrCidadeOrUf(String cep, String logradouro, String numero, String bairro, String cidade, String uf);

    Optional<List<EnderecoModel>> findByClienteModel_Id(Long id);
}

