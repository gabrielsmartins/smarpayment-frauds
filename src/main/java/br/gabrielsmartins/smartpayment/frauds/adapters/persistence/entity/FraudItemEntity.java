package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudItemEntity {

    private FraudItemEntityId id;
    private Long quantity;
    private BigDecimal amount;

    @Data
    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString(exclude = "fraud")
    public static class FraudItemEntityId {

        private FraudEntity fraud;
        private UUID productId;
    }

}
