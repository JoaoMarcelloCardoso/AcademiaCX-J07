package com.commerce.acdemycx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "tb_item")
@Getter
@Setter
@AllArgsConstructor
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private CartModel cart;

    public ItemModel() {
    }
}
