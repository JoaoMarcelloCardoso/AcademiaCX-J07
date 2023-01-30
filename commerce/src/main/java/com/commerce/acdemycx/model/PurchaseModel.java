package com.commerce.acdemycx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "tb_purchase")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    @JsonIgnore
    private CartModel cart;

    @ManyToOne
    @JoinColumn(name = "id_address")
    @JsonIgnore
    private AddressModel address;


    @Column(name = "dataDaCompra")
    private LocalDateTime dataDaCompra;

    private String status;

    private String paymentMethod;

}
