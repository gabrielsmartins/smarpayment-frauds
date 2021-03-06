package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.entity.enums.PaymentMethodData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PaymentMethodPersistenceMapper {

    public PaymentMethodEntity mapToEntity(Map.Entry<PaymentMethod, BigDecimal> paymentMethod){
        return PaymentMethodEntity.builder()
                .withPaymentMethod(PaymentMethodData.fromEnum(paymentMethod.getKey()))
                                  .withAmount(paymentMethod.getValue())
                                  .build();
    }

    public Map.Entry<PaymentMethod, BigDecimal> mapToDomain(PaymentMethodEntity paymentMethodEntity){
        return Map.entry(paymentMethodEntity.getPaymentMethod().getPaymentMethod(), paymentMethodEntity.getAmount());
    }
}
