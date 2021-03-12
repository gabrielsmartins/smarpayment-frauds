package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;

import java.math.BigDecimal;

public class PaymentMethodEntitySupport {

    private PaymentMethodEntitySupport(){
        super();
    }

    public static PaymentMethodEntity.PaymentMethodEntityBuilder defaultPaymentMethodEntity(){
        return PaymentMethodEntity.builder()
                .withPaymentMethod(PaymentMethodData.CASH)
                                   .withAmount(BigDecimal.valueOf(500));
    }
}
