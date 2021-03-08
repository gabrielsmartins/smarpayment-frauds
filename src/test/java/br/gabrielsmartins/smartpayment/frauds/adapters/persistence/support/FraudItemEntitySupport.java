package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.FraudItemEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class FraudItemEntitySupport {

    private FraudItemEntitySupport(){
        super();
    }

    public static FraudItemEntity.FraudItemEntityBuilder defaultFraudItemEntity(){
        return FraudItemEntity.builder()
                              .withId(FraudItemEntity.FraudItemEntityId.builder()
                                      .withProductId(UUID.randomUUID())
                                      .build())
                              .withQuantity(1)
                              .withAmount(BigDecimal.valueOf(500));
    }
}
