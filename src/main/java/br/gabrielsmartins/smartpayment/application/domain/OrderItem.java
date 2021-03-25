package br.gabrielsmartins.smartpayment.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Order order;
    private UUID productId;
    private Integer quantity;
    private BigDecimal amount;

}
