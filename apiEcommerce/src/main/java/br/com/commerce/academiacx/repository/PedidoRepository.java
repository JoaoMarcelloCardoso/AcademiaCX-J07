package br.com.commerce.academiacx.repository;

import br.com.commerce.academiacx.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
