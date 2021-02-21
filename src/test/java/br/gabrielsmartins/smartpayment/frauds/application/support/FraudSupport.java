package br.gabrielsmartins.smartpayment.frauds.application.support;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Fraud.FraudBuilder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FraudSupport {

    private FraudSupport(){
        super();
    }

    public static FraudBuilder defaultFraud(){
        return Fraud.builder()
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500))
                    .withTotalDiscount(BigDecimal.valueOf(500))
                    .withCustomerId(UUID.randomUUID())
                    .withOrderId(12345L)
                    .withId(UUID.randomUUID());
    }
 }
