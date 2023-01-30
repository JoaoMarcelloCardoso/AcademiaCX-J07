package com.commerce.acdemycx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ItemModel> items;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<PurchaseModel> purchases;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private ClientModel clientModel;



}