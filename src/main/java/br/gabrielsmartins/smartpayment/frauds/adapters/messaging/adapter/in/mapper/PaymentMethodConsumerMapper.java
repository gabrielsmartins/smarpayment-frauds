package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PaymentMethodConsumerMapper {

    public Map.Entry<PaymentMethod, BigDecimal> mapToDomain(br.gabrielsmartins.schemas.order_requested.PaymentMethod paymentMethodMessage){
        return Map.entry(PaymentMethod.valueOf(paymentMethodMessage.getPaymentType().name()), paymentMethodMessage.getAmount());
    }

}
