package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.out;

import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.domain.OrderItem;
import br.gabrielsmartins.smartpayment.frauds.application.domain.enums.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static br.gabrielsmartins.smartpayment.frauds.application.support.OrderSupport.defaultOrder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestChannelBinderConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class OrderProducerTest {

    @Autowired
    private OrderProducer producer;

    @Autowired
    private OutputDestination outputDestination;

    @Test
    @DisplayName("Given Order When Is Valid Then Send Message")
    public void givenOrderWhenIsValidThenSendMessage(){

        Order order = defaultOrder().build();

        order.addItem(OrderItem.builder()
                .withId(OrderItem.OrderItemId.builder()
                        .withProductId(UUID.randomUUID())
                        .build())
                .withAmount(BigDecimal.TEN)
                .withQuantity(1L)
                .build());
        order.addPaymentMethod(PaymentMethod.CASH, BigDecimal.TEN);

        this.producer.notify(order)
                     .as(StepVerifier::create)
                     .expectNextCount(1)
                     .verifyComplete();

        Message<byte[]> message = outputDestination.receive();

        assertThat(message).isNotNull();
    }

}
