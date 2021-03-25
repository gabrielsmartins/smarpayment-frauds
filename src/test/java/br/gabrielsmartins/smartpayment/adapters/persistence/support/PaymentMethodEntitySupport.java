package br.gabrielsmartins.smartpayment.adapters.persistence.support;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.enums.PaymentMethodData;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;

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
