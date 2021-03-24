package br.gabrielsmartins.smartpayment.frauds.application.support;

import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis;
import br.gabrielsmartins.smartpayment.frauds.application.domain.FraudAnalysis.FraudAnalysisBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FraudSupport {

    private FraudSupport(){
        super();
    }

    public static FraudAnalysisBuilder defaultFraud(){
        return FraudAnalysis.builder()
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500))
                    .withTotalDiscount(BigDecimal.valueOf(500))
                    .withCustomerId(UUID.randomUUID())
                    .withFraud(true)
                    .withOrderId(12345L)
                    .withId(UUID.randomUUID().toString());
    }
 }
