package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentMethodPersistenceMapperTest {

    private PaymentMethodPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PaymentMethodPersistenceMapper();
    }

    @Test
    @DisplayName("Given Payment Method Entity When Map Payment Method")
    public void givenPaymentMethodEntityWhenMapPaymentMethod(){
        Map.Entry<PaymentMethod, BigDecimal> paymentMethod = Map.entry(PaymentMethod.CASH, BigDecimal.valueOf(1500.00));
        PaymentMethodEntity paymentMethodEntity = this.mapper.mapToEntity(paymentMethod);

        assertThat(paymentMethodEntity).isNotNull();
        assertThat(paymentMethodEntity.getPaymentMethod().getPaymentMethod()).isEqualTo(paymentMethod.getKey());
        assertThat(paymentMethodEntity.getAmount()).isEqualTo(paymentMethod.getValue());
    }

    @Test
    @DisplayName("Given Payment Method When Map Payment Method Entity")
    public void givenPaymentMethodWhenMapPaymentMethodEntity(){
        PaymentMethodEntity paymentMethodEntity = defaultPaymentMethodEntity().build();
        Map.Entry<PaymentMethod, BigDecimal> paymentMethod = this.mapper.mapToDomain(paymentMethodEntity);

        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getKey()).isEqualTo(paymentMethodEntity.getPaymentMethod().getPaymentMethod());
        assertThat(paymentMethod.getValue()).isEqualTo(paymentMethodEntity.getAmount());
    }
}
