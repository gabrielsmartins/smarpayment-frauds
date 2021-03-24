package br.gabrielsmartins.smartpayment.frauds.adapters.messaging.adapter.in.mapper;

import br.gabrielsmartins.schemas.order_received.Item;
import br.gabrielsmartins.schemas.order_received.OrderReceived;
import br.gabrielsmartins.schemas.order_received.PaymentMethod;
import br.gabrielsmartins.schemas.order_received.PaymentType;
import br.gabrielsmartins.smartpayment.frauds.application.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderConsumerMapperTest {

    private OrderConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        var itemConsumerMapper = new OrderItemConsumerMapper();
        var paymentMethodConsumerMapper = new PaymentMethodConsumerMapper();
        this.mapper = new OrderConsumerMapper(itemConsumerMapper, paymentMethodConsumerMapper);
    }

    @Test
    @DisplayName("Given Message When Map Then Return Order")
    public void givenMessageWhenMapThenReturnOrder(){
        var message = OrderReceived.newBuilder()
                                                   .setId(12345L)
                                                   .setCustomerId(UUID.randomUUID().toString())
                                                   .setCreatedAt(LocalDateTime.now())
                                                   .setTotalAmount(BigDecimal.valueOf(1500.00))
                                                   .setTotalDiscount(BigDecimal.valueOf(500.00))
                                                   .setPaymentMethods(List.of(PaymentMethod.newBuilder()
                                                                   .setAmount(BigDecimal.valueOf(100))
                                                                   .setPaymentType(PaymentType.CASH)
                                                                   .build()))
                                                   .setItems(List.of(Item.newBuilder()
                                                            .setProductId(UUID.randomUUID().toString())
                                                            .setQuantity(1)
                                                            .setAmount(BigDecimal.valueOf(1500))
                                                            .build()))
                                                   .build();

        Order order = this.mapper.mapToDomain(message);

        assertThat(order).hasNoNullFieldsOrProperties();
        assertThat(order.getId()).isEqualTo(message.getId());
        assertThat(order.getCreatedAt()).isEqualTo(message.getCreatedAt());
        assertThat(order.getCustomerId().toString()).isEqualTo(message.getCustomerId());
        assertThat(order.getTotalAmount()).isEqualTo(message.getTotalAmount());
        assertThat(order.getTotalDiscount()).isEqualTo(message.getTotalDiscount());
        assertThat(order.getItems().size()).isEqualTo(message.getItems().size());
        assertThat(order.getPaymentMethods().size()).isEqualTo(message.getPaymentMethods().size());
    }

}
