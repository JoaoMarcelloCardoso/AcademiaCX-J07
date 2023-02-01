package com.demo.academiacx.model.dto.compra;

import com.demo.academiacx.model.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompraDto {
    private Long id;

    private  EnderecoModel endereco;

    private ClienteModel cliente;

    private CarrinhoModel carrinho;

    private LocalDateTime data;
}
