package com.commerce.acdemycx.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDtoResponse {
    private Long id;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;
    private String estado;





}
