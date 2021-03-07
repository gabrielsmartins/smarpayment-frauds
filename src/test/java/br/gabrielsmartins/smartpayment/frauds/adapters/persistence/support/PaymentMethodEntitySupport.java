package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;

import java.math.BigDecimal;

public class PaymentMethodEntitySupport {

    private PaymentMethodEntitySupport(){
        super();
    }

    public static PaymentMethodEntity.PaymentMethodEntityBuilder defaultPaymentMethod(){
        return PaymentMethodEntity.builder()
                              .withId(PaymentMethodEntity.PaymentMethodEntityId.builder()
                                      .withPaymentMethod(PaymentMethodData.CASH)
                                      .build())
                              .withAmount(BigDecimal.valueOf(500));
    }
}
