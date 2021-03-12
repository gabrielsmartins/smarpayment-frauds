package br.gabrielsmartins.smartpayment.frauds.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class FraudItem {

    private Fraud fraud;
    private UUID productId;
    private Integer quantity;
    private BigDecimal amount;

}
