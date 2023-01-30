package com.commerce.acdemycx.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String nome;

    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ItemModel> items;



}
