package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.schemas.order_received.PaymentMethod;
import br.gabrielsmartins.schemas.order_received.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentMethodConsumerMapperTest {

    private PaymentMethodConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PaymentMethodConsumerMapper();
    }


    @Test
    @DisplayName("Given Message When Map Then Return Payment Method")
    public void givenMessageWhenMapThenReturnPaymentMethod(){
        var message = PaymentMethod.newBuilder()
                .setPaymentType(PaymentType.CASH)
                .setAmount(BigDecimal.valueOf(1500.00))
                .build();


        var paymentMethod = this.mapper.mapToDomain(message);

        assertThat(paymentMethod.getKey()).isNotNull();
        assertThat(paymentMethod.getValue()).isNotNull();
    }
}
