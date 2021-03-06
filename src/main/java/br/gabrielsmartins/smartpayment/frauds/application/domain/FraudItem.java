package br.gabrielsmartins.smartpayment.frauds.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudItem {

    private FraudItemId id;
    private Long quantity;
    private BigDecimal amount;

    @Data
    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString(exclude = {"fraud"})
    public static class FraudItemId {
        private Fraud fraud;
        private UUID productId;
    }


}
