package br.gabrielsmartins.smartpayment.frauds.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private OrderItemId id;
    private Long quantity;
    private BigDecimal amount;

    @Data
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(exclude = {"order"})
    public static class OrderItemId {
        private Order order;
        private UUID productId;
    }

}
