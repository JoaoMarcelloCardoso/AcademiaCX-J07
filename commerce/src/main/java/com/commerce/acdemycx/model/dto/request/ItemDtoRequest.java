package com.commerce.acdemycx.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDtoRequest {

    private Long id;

    private int quantity;

    private Long product_id;

    private Long cart_id;
}
