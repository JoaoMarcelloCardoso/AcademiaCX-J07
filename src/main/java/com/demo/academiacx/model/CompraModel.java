package com.demo.academiacx.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_compra")
@NoArgsConstructor
@Getter
@Setter
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private  EnderecoModel endereco;

     @ManyToOne
     @JoinColumn(name = "cliente_id")
     private ClienteModel cliente;

     @ManyToOne
     @JoinColumn(name = "carrinho_id")
     private CarrinhoModel carrinho;

    private LocalDateTime data;

    public CompraModel(EnderecoModel endereco, ClienteModel cliente, CarrinhoModel carrinho, LocalDateTime data) {
        this.endereco = endereco;
        this.cliente = cliente;
        this.carrinho = carrinho;
        this.data = data;
    }
}
