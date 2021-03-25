package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudEntity.FraudEntityBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FraudEntitySupport {

    private FraudEntitySupport(){
        super();
    }

    public static FraudEntityBuilder defaultFraudEntity(){
        return FraudEntity.builder()
                    .withCreatedAt(LocalDateTime.now())
                    .withTotalAmount(BigDecimal.valueOf(1500))
                    .withTotalDiscount(BigDecimal.valueOf(500))
                    .withCustomerId(UUID.randomUUID())
                    .withOrderId(12345L)
                    .withId(UUID.randomUUID().toString());
    }
 }
