package br.com.commerce.academiacx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.commerce.academiacx.model.ListaDeDesejos;

@Repository
public interface ListaDeDesejosRepository extends JpaRepository<ListaDeDesejos, Long> {


}
