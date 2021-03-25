package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper;


import br.gabrielsmartins.schemas.fraud_detected.PaymentType;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentMethodProducerMapper {

    public br.gabrielsmartins.schemas.fraud_detected.PaymentMethod mapToFraudDetectedMessage(PaymentMethod paymentMethod, BigDecimal amount){
        return br.gabrielsmartins.schemas.fraud_detected.PaymentMethod.newBuilder()
                                                         .setPaymentType(PaymentType.valueOf(paymentMethod.name()))
                                                         .setAmount(amount)
                                                         .build();
    }

    public br.gabrielsmartins.schemas.fraud_discarded.PaymentMethod mapToFraudDiscardedMessage(PaymentMethod paymentMethod, BigDecimal amount){
        return br.gabrielsmartins.schemas.fraud_discarded.PaymentMethod.newBuilder()
                .setPaymentType(br.gabrielsmartins.schemas.fraud_discarded.PaymentType.valueOf(paymentMethod.name()))
                .setAmount(amount)
                .build();
    }
}
