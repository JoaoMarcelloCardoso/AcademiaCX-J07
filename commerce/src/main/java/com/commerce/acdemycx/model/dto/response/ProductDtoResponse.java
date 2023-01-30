package com.commerce.acdemycx.model.dto.response;

import com.commerce.acdemycx.model.ItemModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {
    private Long id;

    private String nome;

    private Double price;

}
