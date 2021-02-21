package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out.mapper;

import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
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
    @DisplayName("Given Payment Method When Map Then Return Message")
    public void givenPaymentMethodWhenMapThenReturnMessage(){
        PaymentMethod paymentMethod = PaymentMethod.CASH;
        BigDecimal amount = BigDecimal.valueOf(1500);

        br.gabrielsmartins.schemas.order_validated.PaymentMethod paymentMethodMessage = this.mapper.mapToMessage(paymentMethod, amount);

        assertThat(paymentMethodMessage).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodMessage.getPaymentType().name()).isEqualTo(paymentMethod.name());
        assertThat(paymentMethodMessage.getAmount()).isEqualTo(amount);
    }

}
