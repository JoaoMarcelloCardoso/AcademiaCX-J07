package com.commerce.acdemycx.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String complemento;


    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private ClientModel clientModel;
}
