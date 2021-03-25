package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentMethodProducerMapperTest {

    private PaymentMethodProducerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PaymentMethodProducerMapper();
    }

    @Test
    @DisplayName("Given Payment Method When Map Then Return Fraud Detected Message")
    public void givenPaymentMethodWhenMapThenReturnFraudDetectedMessage(){
        PaymentMethod paymentMethod = PaymentMethod.CASH;
        BigDecimal amount = BigDecimal.valueOf(1500);

        var paymentMethodMessage = this.mapper.mapToFraudDetectedMessage(paymentMethod, amount);

        assertThat(paymentMethodMessage).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodMessage.getPaymentType().name()).isEqualTo(paymentMethod.name());
        assertThat(paymentMethodMessage.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("Given Payment Method When Map Then Return Fraud Discarded Message")
    public void givenPaymentMethodWhenMapThenReturnFraudDiscardedMessage(){
        PaymentMethod paymentMethod = PaymentMethod.CASH;
        BigDecimal amount = BigDecimal.valueOf(1500);

        var paymentMethodMessage = this.mapper.mapToFraudDiscardedMessage(paymentMethod, amount);

        assertThat(paymentMethodMessage).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodMessage.getPaymentType().name()).isEqualTo(paymentMethod.name());
        assertThat(paymentMethodMessage.getAmount()).isEqualTo(amount);
    }

}
