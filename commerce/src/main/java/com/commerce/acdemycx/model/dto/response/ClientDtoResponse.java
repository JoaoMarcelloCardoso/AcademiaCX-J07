package com.commerce.acdemycx.model.dto.response;

import com.commerce.acdemycx.model.AddressModel;
import com.commerce.acdemycx.model.CartModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDtoResponse {

    private Long id;
    private String cpf;
    private String nome;
    private String username;
    private List<AddressModel> addresses;
    private List<CartModel> carts;

}
