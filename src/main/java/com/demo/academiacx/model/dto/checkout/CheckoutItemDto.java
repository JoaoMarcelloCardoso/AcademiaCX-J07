package com.demo.academiacx.model.dto.checkout;

import com.demo.academiacx.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutItemDto {
    private Long pagamento_id;
    private Long endereco_id;
    private Long carrinho_id;

}
