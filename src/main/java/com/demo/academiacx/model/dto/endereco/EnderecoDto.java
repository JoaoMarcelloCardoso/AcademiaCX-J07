package com.demo.academiacx.model.dto.endereco;

import com.demo.academiacx.model.EnderecoModel;
import com.demo.academiacx.model.dto.user.ClienteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private Long cliente_id;


    public EnderecoDto() {

    }

    public EnderecoDto(EnderecoModel enderecoModel) {
        this.id = enderecoModel.getId();
        this.cep = enderecoModel.getCep();
        this.cidade = enderecoModel.getCidade();
        this.uf = enderecoModel.getUf();
        this.bairro = enderecoModel.getBairro();
        this.logradouro = enderecoModel.getLogradouro();
        this.numero = enderecoModel.getNumero();
        this.cliente_id = enderecoModel.getCliente().getId();
    }
}
