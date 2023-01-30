package com.commerce.acdemycx.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDtoRequest {

    private Long id;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String complemento;

    private Long client_id;



    public String getCep() {
        return cep.replaceAll("[^0-9]", "");
    }
}
