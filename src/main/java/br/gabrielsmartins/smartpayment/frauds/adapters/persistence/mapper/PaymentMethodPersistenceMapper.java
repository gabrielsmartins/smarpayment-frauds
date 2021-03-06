package br.gabrielsmartins.smartpayment.frauds.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.frauds.adapters.persistence.entity.enums.PaymentMethodData;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PaymentMethodPersistenceMapper {

    public Map.Entry<PaymentMethodData, BigDecimal> mapToEntity(Map.Entry<PaymentMethod, BigDecimal> paymentMethod){
        return Map.entry(PaymentMethodData.fromEnum(paymentMethod.getKey()), paymentMethod.getValue());
    }

    public Map.Entry<PaymentMethod, BigDecimal> mapToDomain(Map.Entry<PaymentMethodData, BigDecimal> paymentMethod){
        return Map.entry(paymentMethod.getKey().getPaymentMethod(), paymentMethod.getValue());
    }
}
