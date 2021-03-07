package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.in;

import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.schemas.order_requested.OrderRequested;
import br.gabrielsmartins.schemas.order_requested.PaymentMethod;
import br.gabrielsmartins.schemas.order_requested.PaymentType;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import br.gabrielsmartins.smartpayment.frauds.application.ports.in.ProcessOrderUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import({TestChannelBinderConfiguration.class})
public class OrderConsumerTest {

    @Autowired
    private OrderConsumer consumer;

    @MockBean
    private ProcessOrderUseCase useCase;

    @Autowired
    private InputDestination inputDestination;


    @Test
    @DisplayName("Given Message When Consume Then Return Processed Order")
    public void givenMessageWhenConsumeThenReturnProcessedOrder(){


        OrderRequested orderRequested = OrderRequested.newBuilder()
                                                        .setId(12345L)
                                                        .setCreatedAt(LocalDateTime.now())
                                                        .setTotalAmount(BigDecimal.valueOf(1500))
                                                        .setTotalDiscount(BigDecimal.valueOf(500))
                                                        .setCustomerId(UUID.randomUUID().toString())
                                                        .setItems(List.of(Item.newBuilder()
                                                                .setProductId(UUID.randomUUID().toString())
                                                                .setAmount(BigDecimal.valueOf(100))
                                                                .setQuantity(1)
                                                                .build()))
                                                        .setPaymentMethods(List.of(PaymentMethod.newBuilder()
                                                                .setPaymentType(PaymentType.CASH)
                                                                .setAmount(BigDecimal.valueOf(100))
                                                                .build()))
                                                        .build();

        Message<OrderRequested> message = MessageBuilder.withPayload(orderRequested)
                                                       .build();

        when(useCase.process(any(Order.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        inputDestination.send(message);

        verify(useCase, timeout(5000)).process(any(Order.class));
    }


}
