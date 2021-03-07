package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity;


import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {

    private PaymentMethodEntityId id;
    private BigDecimal amount;

    @Data
    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString(exclude = "fraud")
    public static class PaymentMethodEntityId{

        private FraudEntity fraud;
        private PaymentMethodData paymentMethod;

    }
}
