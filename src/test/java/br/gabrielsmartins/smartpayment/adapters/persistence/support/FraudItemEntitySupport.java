package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.FraudItemEntity;

import java.math.BigDecimal;
import java.util.UUID;

public class FraudItemEntitySupport {

    private FraudItemEntitySupport(){
        super();
    }

    public static FraudItemEntity.FraudItemEntityBuilder defaultFraudItemEntity(){
        return FraudItemEntity.builder()
                              .withProductId(UUID.randomUUID())
                              .withQuantity(1)
                              .withAmount(BigDecimal.valueOf(500));
    }
}
