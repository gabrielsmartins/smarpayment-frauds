package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;


import br.gabrielsmartins.schemas.order_validated.PaymentType;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentMethodProducerMapper {

    public br.gabrielsmartins.schemas.order_validated.PaymentMethod mapToMessage(PaymentMethod paymentMethod, BigDecimal amount){
        return br.gabrielsmartins.schemas.order_validated.PaymentMethod.newBuilder()
                                                         .setPaymentType(PaymentType.valueOf(paymentMethod.name()))
                                                         .setAmount(amount)
                                                         .build();
    }

}
